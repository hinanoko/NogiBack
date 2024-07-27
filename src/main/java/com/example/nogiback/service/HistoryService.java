package com.example.nogiback.service;

import com.example.nogiback.entity.BlogCount;
import com.example.nogiback.entity.BlogSummary;
import com.example.nogiback.entity.History;
import com.example.nogiback.mapper.HistoryMapper;
import com.example.nogiback.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class HistoryService {

    private final HistoryMapper historyMapper;

    private final String uploadPath = "F://Project/FutureCider/NogiBack/src/main/java/com/example/nogiback/pictures/";

    @Autowired
    public HistoryService(HistoryMapper historyMapper) {
        this.historyMapper = historyMapper;
    }

    public String addHistoryBlog(History history, String token) {
        if (JwtUtil.checkToken(token)) {
            System.out.println("token right");

            if (history.getBlogDate() == null || history.getBlogDate().isEmpty()) {
                history.setBlogDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }

            historyMapper.insertNewBlog(history);
            Integer blogId = history.getBlogId();
            System.out.println(blogId);

            // 获取成员ID
            String memberId = history.getBlogIdolId();
            if (memberId == null || memberId.isEmpty()) {
                throw new RuntimeException("Member ID is required");
            }

            // 确定成员文件夹路径
            String memberFolderPath = uploadPath + memberId;
            File memberFolder = new File(memberFolderPath);
            if (!memberFolder.exists() || !memberFolder.isDirectory()) {
                throw new RuntimeException("Member folder does not exist: " + memberId);
            }

            // 创建以日期和blogId命名的文件夹
            String dateFolderName = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "_" + String.format("%08d", blogId);
            String dateFolderPath = memberFolderPath + "/" + dateFolderName;
            File dateFolder = new File(dateFolderPath);
            if (!dateFolder.exists()) {
                dateFolder.mkdirs();
            }

            // 保存图片到新的文件夹中并收集内容块
            StringBuilder contentBuilder = new StringBuilder();
            contentBuilder.append("# ").append(history.getTitle()).append("\n\n");

            for (History.ContentBlock block : history.getContentBlocks()) {
                if ("image".equals(block.getType())) {
                    String base64Image = block.getValue();
                    String fileName = saveImage(base64Image, dateFolderPath);
                    contentBuilder.append("![Image](").append(fileName).append(")\n\n"); // Markdown格式的图片插入
                    block.setValue(fileName); // 替换base64内容为文件名
                } else if ("text".equals(block.getType())) {
                    contentBuilder.append(block.getValue()).append("\n\n");
                }
            }

            // 保存内容到content.md
            saveContent(contentBuilder.toString(), dateFolderPath);

            return "success";
        } else {
            throw new RuntimeException("Invalid token");
        }
    }

    private String saveImage(String base64Image, String folderPath) {
        // 移除可能存在的 "data:image/png;base64," 前缀
        String[] parts = base64Image.split(",");
        String imageString = parts.length > 1 ? parts[1] : parts[0];

        // 解码base64
        byte[] decodedBytes = Base64.getDecoder().decode(imageString);
        String fileName = System.currentTimeMillis() + ".png"; // 生成唯一文件名
        File file = new File(folderPath + "/" + fileName);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(decodedBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName; // 返回文件名
    }

    private void saveContent(String content, String folderPath) {
        File file = new File(folderPath + "/content.md");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<BlogCount> getBlogCounts() {
        return historyMapper.countBlogsByIdolId();
    }

    // 新增的方法：获取指定成员的博客简要信息
    public List<BlogSummary> getBlogSummariesByIdolId(String idolId) {
        List<Integer> blogIds = historyMapper.findBlogIdsByIdolId(idolId);
        List<BlogSummary> summaries = new ArrayList<>();

        for (Integer blogId : blogIds) {
            BlogSummary summary = new BlogSummary();
            summary.setBlogId(blogId);

            // Construct the blog folder path
            String blogDirPath = uploadPath + idolId;
            File blogDir = new File(blogDirPath);

            // Find the folder for this blog post
            File[] blogFolders = blogDir.listFiles((dir, name) -> name.endsWith("_" + String.format("%08d", blogId)));

            if (blogFolders != null && blogFolders.length > 0) {
                File blogFolder = blogFolders[0];
                File contentFile = new File(blogFolder, "content.md");

                if (contentFile.exists()) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(contentFile))) {
                        String line;
                        boolean firstImageFound = false;

                        while ((line = reader.readLine()) != null) {
                            if (line.startsWith("# ")) {
                                summary.setTitle(line.substring(2).trim());
                            } else if (line.startsWith("![Image](") && !firstImageFound) {
                                String imagePath = line.substring(9, line.length() - 1);
                                File imageFile = new File(blogFolder, imagePath);
                                if (imageFile.exists()) {
                                    byte[] imageBytes = Files.readAllBytes(Paths.get(imageFile.getAbsolutePath()));
                                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                                    summary.setFirstImage("data:image/png;base64," + base64Image);
                                    firstImageFound = true;
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                // Extract date from folder name
                String folderName = blogFolder.getName();
                String dateStr = folderName.substring(0, 8); // Assuming format is yyyyMMdd_blogId
                try {
                    Date date = new SimpleDateFormat("yyyyMMdd").parse(dateStr);
                    summary.setBlogDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            summaries.add(summary);
        }
        return summaries;
    }
}
