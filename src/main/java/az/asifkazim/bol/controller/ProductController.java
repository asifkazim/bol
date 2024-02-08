package az.asifkazim.bol.controller;

import az.asifkazim.bol.entity.Product;
import az.asifkazim.bol.service.ProductService;
import az.asifkazim.bol.util.FileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        super();
        this.productService = productService;
    }

    // handler method to handle list students and return mode and view
    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/product")
    public String searchProducts(@RequestParam("name") String name,
            Model model) {
        model.addAttribute("products", productService.getAllProductsByName(name));
        return "products";
    }

    @GetMapping("/catalog")
    public String searchCatalogs(@RequestParam("name") String name,
                                 Model model) {
        model.addAttribute("products", productService.getAllCatalogsByName("active",name));
        return "catalog";
    }

    @PostMapping("/products")
    public String saveProduct(@ModelAttribute("product") Product product,
                              @RequestParam("image") MultipartFile multipartFile) throws Exception {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println(fileName);
        product.setPhotos(fileName);

        Product savedUser = productService.saveProduct(product);

        String uploadDir = "public/user-photos/" + savedUser.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return "redirect:/products";
    }

    @GetMapping("/products/new")
    public String createProductForm(Model model) {
        // create product object to hold product form data
        Product product = new Product();
        model.addAttribute("product", product);
        return "create_product";

    }


    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "edit_product";
    }



    @PostMapping("/products/{id}")
    public String updateProduct(@ModelAttribute("product") Product product,
                                @RequestParam("image") MultipartFile multipartFile
    ) throws IOException {


        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if (!fileName.isEmpty()) {
            System.out.println("filename bos deyil");
            String deletedDir = "public/user-photos/" + product.getId();
            FileUploadUtil.deleteFolder(deletedDir);
            product.setPhotos(fileName);
        }else
        {
            product.setPhotos(productService.getProductById(product.getId()).getPhotos());
        }

        System.out.println("filename bos dur");

        Product savedUser = productService.saveProduct(product);

        if (!fileName.isEmpty()) {
            String uploadDir = "public/user-photos/" + savedUser.getId();

            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }

        return "redirect:/products";
    }

    // handler method to handle delete student request

    @GetMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id) throws IOException {
        productService.deleteProductById(id);
        String deletedDir = "public/user-photos/" + id;
        FileUploadUtil.deleteFolder(deletedDir);
        return "redirect:/products";
    }

    @GetMapping("/catalogs")
    public String listCatalogs(Model model) {
        model.addAttribute("products", productService.getAllProductsForCatalog("active"));
        return "catalog";
    }


    @GetMapping("/print")
    public String printCatalog(Model model) {
        model.addAttribute("products", productService.getAllProductsForCatalog("active"));
        return "catalog_print";
    }

    @GetMapping("/catalogs/edit/{id}")
    public String addCatalogForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        product.setStatus("active");
        productService.saveProduct(product);
        model.addAttribute("product", product);
        return "redirect:/products";
    }

    @GetMapping("/catalogs/{id}")
    public String deleteCatalog(@PathVariable Long id) throws IOException {
        Product product = productService.getProductById(id);
        product.setStatus("inActive");
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/catalogs/delete/{id}")
    public String deleteCatalogFromCatalogPage(@PathVariable Long id) throws IOException {
        Product product = productService.getProductById(id);
        product.setStatus("inActive");
        productService.saveProduct(product);
        return "redirect:/catalogs";
    }

}
