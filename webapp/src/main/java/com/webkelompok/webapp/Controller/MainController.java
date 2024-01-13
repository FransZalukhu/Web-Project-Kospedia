package com.webkelompok.webapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webkelompok.webapp.Model.Register;
import com.webkelompok.webapp.Model.RegisterKos;
import com.webkelompok.webapp.Model.OwnerRegister;
import com.webkelompok.webapp.Model.Pemesanan;
import com.webkelompok.webapp.dao.PemesananDao;
import com.webkelompok.webapp.dao.RegistKosdao;
import com.webkelompok.webapp.dao.registerdao;
import com.webkelompok.webapp.dao.ownerregistdao;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private registerdao dao;

    @Autowired
    private RegistKosdao registKosdao;

    @Autowired
    private ownerregistdao ownerdao;

    @Autowired
    private PemesananDao pemesanandao;

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/homeowner")
    public String homeOwner() {
        return "indexowner";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String sekedar() {
        return "loginoption";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/loginowner")
    public String loginowner() {
        return "loginowner";
    }

    @GetMapping("/registform")
    public String register() {
        return "registform";
    }

    @GetMapping("/ownerregister")
    public String registowner() {
        return "ownerregister";
    }

    @GetMapping("/tambahkos")
    public String tambahkos() {
        return "formtambahkos";
    }

    // @GetMapping("/pesan")
    // public String pemesanan() {
    // return "formpemesanan";
    // }

    // @GetMapping("/pesan")
    // public String showPesanForm(Model model) {
    // List<RegisterKos> kosList = registKosdao.findAll();
    // model.addAttribute("kosList", kosList);
    // model.addAttribute("pemesanan", new Pemesanan());
    // return "formpemesanan";
    // }

    @GetMapping("/pesan")
    public String showPesanForm(Model model) {
        return "formpemesanan";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Register register) {
        dao.save(register);
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Register register = dao.findByEmail(email);
        if (register != null && register.getPassword().equals(password)) {
            session.setAttribute("loggedInUser", register);
            return "index";
        } else {
            return "login";
        }
    }

    @PostMapping("/ownerregister")
    public String registerowner(@ModelAttribute OwnerRegister owner) {
        ownerdao.save(owner);
        return "loginowner";
    }

    // @PostMapping("/loginowner")
    // public String logiPemilik(@RequestParam String email, @RequestParam String
    // password) {
    // OwnerRegister registerowner = ownerdao.findByEmail(email);
    // if (registerowner != null && registerowner.getPassword().equals(password)) {
    // return "indexowner";
    // } else {
    // return "loginowner";
    // }
    // }

    @PostMapping("/loginowner")
    public String logiPemilik(@RequestParam String email, @RequestParam String password, HttpSession session) {
        OwnerRegister registerowner = ownerdao.findByEmail(email);
        if (registerowner != null && registerowner.getPassword().equals(password)) {
            session.setAttribute("loggedInOwner", registerowner);
            return "indexowner";
        } else {
            return "loginowner";
        }
    }

    @PostMapping("/tambahkos")
    public String tambahkos(@ModelAttribute RegisterKos registerKos, HttpSession session) {
        OwnerRegister owner = (OwnerRegister) session.getAttribute("loggedInOwner");
        if (owner != null) {
            registerKos.setOwner(owner);
            registKosdao.save(registerKos);
            return "indexowner";
        } else {
            // Tampilkan pesan error atau lakukan pengalihan karena pemilik tidak login
            return "loginowner";
        }
    }

    // @PostMapping("/pesan")
    // public String pesan(@RequestParam Integer idkos, HttpSession session) {
    // Register user = (Register) session.getAttribute("loggedInUser");
    // RegisterKos kos = registKosdao.findById(idkos).orElse(null);
    // if (user != null && kos != null) {
    // Pemesanan pemesanan = new Pemesanan();
    // pemesanan.setUser(user);
    // pemesanan.setKos(kos);
    // pemesanandao.save(pemesanan);
    // return "indexUser";
    // } else {
    // return "errorPage";
    // }
    // }

    @PostMapping("/pesan")
    public String pesan(HttpSession session,
            @ModelAttribute Pemesanan pemesanan) {
        Register user = (Register) session.getAttribute("loggedInUser");
        if (user != null) {
            pemesanan.setUser(user);
            pemesanandao.save(pemesanan);
            return "index";
        } else {
            return "formpemesanan";
        }
    }

    @GetMapping("/readpemesanan")
    public String readPemesanan(Model model) {
        List<Pemesanan> pemesananList = pemesanandao.findAll();
        model.addAttribute("pemesananList", pemesananList);
        return "readpemesanan";
    }

    @GetMapping("/pemesanan/edit/{id}")
    public String showEditPemesananForm(@PathVariable Integer id, Model model, HttpSession session) {
        Register user = (Register) session.getAttribute("loggedInUser");
        if (user != null) {
            Pemesanan pemesanan = pemesanandao.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid pemesanan Id:" + id));
            model.addAttribute("pemesanan", pemesanan);
            return "editpemesanan";
        } else {
            return "readpemesanan";
        }
    }

    @PostMapping("/pemesanan/update/{id}")
    public String updatePemesanan(@PathVariable Integer id, @ModelAttribute Pemesanan pemesanan) {
        pemesanan.setUser(dao.findByUsername(pemesanan.getUser().getUsername()));
        pemesanandao.save(pemesanan);
        return "redirect:/readpemesanan";
    }

    @GetMapping("/pemesanan/delete/{id}")
    public String deletePemesanan(@PathVariable Integer id) {
        pemesanandao.deleteById(id);
        return "redirect:/readpemesanan";
    }

    @GetMapping("/readpemesananowner")
    public String readPemesananowner(Model model) {
        List<Pemesanan> pemesananList = pemesanandao.findAll();
        model.addAttribute("pemesananList", pemesananList);
        return "readpemesananowner";
    }
}
