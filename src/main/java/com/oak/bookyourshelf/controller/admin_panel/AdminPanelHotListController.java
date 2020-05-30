package com.oak.bookyourshelf.controller.admin_panel;

import com.oak.bookyourshelf.Globals;
import com.oak.bookyourshelf.model.Campaign;
import com.oak.bookyourshelf.model.Category;
import com.oak.bookyourshelf.model.HotList;
import com.oak.bookyourshelf.model.Subcategory;
import com.oak.bookyourshelf.service.admin_panel.AdminPanelCategoryService;
import com.oak.bookyourshelf.service.admin_panel.AdminPanelHotListService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class AdminPanelHotListController {
    final AdminPanelCategoryService adminPanelCategoryService;
    final AdminPanelHotListService adminPanelHotListService;

    public AdminPanelHotListController(AdminPanelCategoryService adminPanelCategoryService, AdminPanelHotListService adminPanelHotListService) {
        this.adminPanelCategoryService = adminPanelCategoryService;
        this.adminPanelHotListService = adminPanelHotListService;
    }

    @RequestMapping(value = "/admin-panel/hotList", method = RequestMethod.GET)
    public String tab(@RequestParam("page") Optional<Integer> page,
                      @RequestParam("size") Optional<Integer> size, Model model) {

        Globals.getPageNumbers(page, size, (List) adminPanelHotListService.listAll(), model, "hotListPage");

        HotList hotList = new HotList();
        model.addAttribute("hotList", hotList);
        model.addAttribute("categoryService", adminPanelCategoryService);


        return "admin_panel/_hotList";
    }


    @RequestMapping(value = "/admin-panel/hotList/category", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> findAllCategories(@RequestParam String category) {

        if (category.equals("BOOK")) {
            System.out.println("mkdfg");
            System.out.println(adminPanelCategoryService.getAllByCategory("Book"));
            return  (List<Category>) adminPanelCategoryService.getAllByCategory("Book");
        }
        else if(category.equals("E_BOOK"))
            return  (List<Category>) adminPanelCategoryService.getAllByCategory("E-Book");
        else if(category.equals("AUDIO_BOOK"))
            return  (List<Category>) adminPanelCategoryService.getAllByCategory("Audio Book");
        else return null;

    }

    @RequestMapping(value = "/admin-panel/hotList/subcategory", method = RequestMethod.GET)
    @ResponseBody
    public List<Subcategory> findAllSubcategories(@RequestParam String category) {
        return Globals.getAllSubcategories(adminPanelCategoryService.getByName(category));
    }



    @RequestMapping(value = "/admin-panel/hotList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> saveCategory(@RequestParam String ctg, HotList hotList) {
        System.out.println("post method");
        System.out.println(ctg);
        adminPanelHotListService.save(hotList);
        return ResponseEntity.ok("");
    }
}
