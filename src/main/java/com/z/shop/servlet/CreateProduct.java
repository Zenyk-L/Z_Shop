package com.z.shop.servlet;

import com.z.shop.entity.Category;
import com.z.shop.entity.Language;
import com.z.shop.entity.Product;
import com.z.shop.service.CategoryService;
import com.z.shop.service.LanguageService;
import com.z.shop.service.ProductService;
import com.z.shop.service.impl.CategoryServiceImpl;
import com.z.shop.service.impl.LanguageServiceImpl;
import com.z.shop.service.impl.ProductServiceImpl;
import com.z.shop.utils.DBManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.*;

@WebServlet(name = "CreateProduct", value = "/createProduct")
@MultipartConfig
public class CreateProduct extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(CreateProduct.class);

    private ProductService productService = ProductServiceImpl.getProductService();
    private CategoryService categoryService = CategoryServiceImpl.getCategoryServiceImpl();
    private LanguageService languageService = LanguageServiceImpl.getLanguageServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = new Product();

        List<Category> categories = categoryService.readAll();
        List<Language> languages = languageService.readAll();
        request.setAttribute("product", product);
        request.setAttribute("categories", categories);
        request.setAttribute("languages", languages);
        request.getRequestDispatcher("createProduct.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectCategoryValue = request.getParameter("category");
        if ("add".equals(selectCategoryValue)) {
            selectCategoryValue=request.getParameter("newLanguage_en");
            Category category = new Category();
            category.setName(selectCategoryValue);

            Map<String, String> categoryTranslations = new HashMap<>();
            Iterator<Language> languageIterator = languageService.readAll().iterator();

            while(languageIterator.hasNext()){
                String shortName = languageIterator.next().getShortName();
                System.out.println("newLanguage_"+shortName);
                System.out.println(request.getParameter("newLanguage_"+shortName));
                categoryTranslations.put(shortName,request.getParameter("newLanguage_"+shortName));
            }
            category.setTranslations(categoryTranslations);
            categoryService.create(category);

        }


        Product product = new Product();
        product.setName(request.getParameter("name"));
        product.setCategory(selectCategoryValue);
        product.setImage(processRequest(request, response));
        product.setQuantity(Integer.valueOf(request.getParameter("quantity")));
        product.setDescription(request.getParameter("description"));
        product.setColor(request.getParameter("color"));
        product.setScale(request.getParameter("scale"));
        product.setPrice(Double.valueOf(request.getParameter("price")));
        product.setAddingDate(new Date());


        productService.create(product);

        response.sendRedirect("createProduct.jsp");
    }

// saving image file from form to local directory witch is set in   DBManager.URL_TO_IMAGE_FOLDER and return file name

    protected String processRequest(HttpServletRequest request,
                                    HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Create path components to save the file

        // // setting absolute path
        String path = DBManager.URL_TO_IMAGE_FOLDER;

        // setting relative path
        String prefix = getServletContext().getRealPath("/");
        String[] targets = prefix.split("target");
        path = targets[0] + "src/main/webapp/image";


        final Part filePart = request.getPart("file");
        String fileName = "";
        if (filePart.getSize() > 0) {
            fileName = getUniqueFileName(filePart);
            System.out.println();

            OutputStream out = null;
            InputStream filecontent = null;
            PrintWriter writer = null;

            try {
                out = new FileOutputStream(new File(path + File.separator
                        + fileName));
                filecontent = filePart.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                LOGGER.info("File " + fileName + " being uploaded to " + path);
            } catch (FileNotFoundException fne) {
                writer = response.getWriter();
                writer.println("You either did not specify a file to upload or are "
                        + "trying to upload a file to a protected or nonexistent "
                        + "location.");
                writer.println("<br/> ERROR: " + fne.getMessage());

                LOGGER.error("Problems during file upload. Error: " + fne.getMessage());
            } finally {
                if (out != null) {
                    out.close();
                }
                if (filecontent != null) {
                    filecontent.close();
                }
                if (writer != null) {
                    writer.close();
                }
            }
        }
        return fileName;
    }

//    extract from part file type of file, and return generated unique file name with type original file

    private String getUniqueFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.info("Part Header = " + partHeader + " from form");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                String[] split = content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "").split("\\.");
                String fileType = split[split.length - 1];

                return new Date().getTime() + "." + fileType;
            }
        }
        return "";
    }

}
