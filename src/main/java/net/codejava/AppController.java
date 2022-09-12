package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	@Autowired
	private ProductService service; 
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Product> listProducts = service.listAll();
		model.addAttribute("listProducts", listProducts);
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		
		return "new_product";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		service.save(product);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_product");
		Product product = service.get(id);
		mav.addObject("product", product);
		
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";		
	}

	@GetMapping("/getProducts")
	public ResponseEntity<List<Product>> read() {
		List<Product> listaProd = service.listAll();
	    if (listaProd == null) {
	        return ResponseEntity.notFound().build();
	    } else {
	        return ResponseEntity.ok(listaProd);
	    }
	}
	
	@GetMapping("/getProducts/{brand}")
	public ResponseEntity<List<Product>> readByBrand(@PathVariable(name = "brand") String brand) {
		List<Product> listaByBrand = service.listByBrand(brand);
	    if (listaByBrand == null) {
	        return ResponseEntity.notFound().build();
	    } else {
	        return ResponseEntity.ok(listaByBrand);
	    }
	}
	
	
	@GetMapping("/getProducts/byPrice/{price}")
	public ResponseEntity<List<Product>> readByPrice(@PathVariable(name = "price") float price) {
		List<Product> listaByPrice = service.listByPrice(price);
	    if (listaByPrice == null) {
	        return ResponseEntity.notFound().build();
	    } else {
	        return ResponseEntity.ok(listaByPrice);
	    }
	}
	
	
}
