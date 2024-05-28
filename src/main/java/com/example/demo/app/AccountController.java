package com.example.demo.app;

import com.example.demo.domain.model.dto.AccountRequest;
import com.example.demo.domain.model.entity.Store;
import com.example.demo.domain.service.AccountService;
import com.example.demo.domain.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AccountController {
    private AccountService accountService;
    private StoreService storeService;

    public AccountController(AccountService accountService, StoreService storeService) {
        this.accountService = accountService;
        this.storeService = storeService;
    }


    @GetMapping("/account")
    public String getAccount(Model model) {
        model.addAttribute("accRequest", new AccountRequest());
        return "account.html";
    }

    @PostMapping("/account")
    public String postAccount(Model model, @ModelAttribute("accRequest") AccountRequest accRequest) {
        System.out.println(accRequest.getId());
        System.out.println(accRequest.getPassword());
        System.out.println(accRequest.getName());
        accountService.save(accRequest);
        return "accountOutput.html";
    }

    @GetMapping("/storeList")
    public String getStoreList(Model model) {
        List<Store> stores = storeService.findAll();
        model.addAttribute("stores", stores);
        return "storeList.html";
    }
}