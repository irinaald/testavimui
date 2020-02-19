package com.spring.calculator;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

// @RestController negrąžina view.
// Kadangi mums reikia grąžinti view pagal Spring MVC, naudojame @Controller
@Controller
//  žymi konfigūracijos komponentą. Viduje leidžia kurti bean per metodus su @Bean
//  Ši klasės lygio anotacija nurodo Spring karkasui “atspėti” konfigūraciją,
//  remiantis priklausomybėmis (jar bibliotekos), kurias programuotojas įtraukė į projektą.
//  Šiuo atveju ji veikia kartu su main metodu.
@EnableAutoConfiguration
public class CalculatorController {
    // Maršrutizavimo informacija. Šiuo atveju, ji nurodo Spring karkasui,
    // jog visas HTTP užklausas, kurių kelias yra “/” apdoros metodas “home”.
    @RequestMapping(method = RequestMethod.GET, value = "/")
    String home(Model model) {
        // Jeigu Model 'number' nepraeina validacijos - per jį grąžinamos validacijos klaidos į View
        model.addAttribute("number", new Number());
        // grąžiname JSP failą, turi būti talpinami 'webapp -> WEB-INF -> jsp' aplanke
        return "skaiciuotuvas";
    }

    // Kadangi skaičiuotuvo forma naudoja POST metodą, čia irgi nurodome POST
    // SVARBU: parametras BindingResult turi eiti iškarto po anotacijos @Valid
    // kitu atveju bus 'Validation failed for object'
    @RequestMapping(method = RequestMethod.POST, value = "/skaiciuoti")
    String skaiciuoti(@Valid @ModelAttribute("number") Number e, BindingResult br,
                      @RequestParam HashMap<String, String> skaiciai, ModelMap modelMap) {

        if(br.hasErrors()){ // jeigu validacijos klaidos (žr. Number klasėje aprašytą validaciją prie kiekvieno skaičiaus)
            return "skaiciuotuvas"; // vartotojas lieka skaičiuotuvo lange tol, kol neištaiso validacijos klaidų
        } else { // praėjo validaciją- skaičiuojamas rezultatas
            int sk1 = Integer.parseInt(skaiciai.get("sk1"));
            int sk2 = Integer.parseInt(skaiciai.get("sk2"));
            String zenklas = skaiciai.get("zenklas");

            int rezultatas = 0;
            if (zenklas.equals("+")) {
                rezultatas = sk1 + sk2;
            } else if (zenklas.equals("-")) {
                rezultatas = sk1 - sk2;
            } else if (zenklas.equals("*")) {
                rezultatas = sk1 * sk2;
            } else if (zenklas.equals("/")) {
                rezultatas = sk1 / sk2;
            }

            // ModelMap objektas naudojamas siųsti reikšmes iš Spring MVC controller į JSP
            modelMap.put("sk1", sk1);
            modelMap.put("sk2", sk2);
            modelMap.put("zenklas", zenklas);
            modelMap.put("rezultatas", rezultatas);

            return "skaiciuoti";
        }
    }
}
