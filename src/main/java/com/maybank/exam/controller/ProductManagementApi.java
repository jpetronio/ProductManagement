package com.maybank.exam.controller;

import com.maybank.exam.models.ProductDetailsDTO;
import com.maybank.exam.service.ProductMgmtService;
import com.maybank.exam.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductManagementApi {

    @Autowired
    private ProductMgmtService productMgmtService;

    @Autowired
    private SecurityService securityService;

    @PostMapping
    public ResponseEntity<String> saveProduct(@RequestHeader(value="Authorization", required=true) String authorization,
                                              @RequestBody ProductDetailsDTO productDTO) {
        if(securityService.verifyUserAuthentication(authorization)){
            String resp = productMgmtService.save(productDTO,"S");
            return resp.startsWith("S") ? new ResponseEntity<>(resp, HttpStatus.OK)
                                        : new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }else return new ResponseEntity<>("User not Authorized", HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@RequestHeader(value="Authorization", required=true) String authorization,
                                         @PathVariable int id) {
        if(securityService.verifyUserAuthentication(authorization)) {
            if (productMgmtService.findProductById(id)) {
                productMgmtService.deleteById(id);
                return new ResponseEntity<>("", HttpStatus.OK);
            } else return new ResponseEntity<>("Product Id not existing", HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>("User not Authorized", HttpStatus.UNAUTHORIZED);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestHeader(value="Authorization", required=true) String authorization,
                                         @RequestBody ProductDetailsDTO productDTO) {
        if(securityService.verifyUserAuthentication(authorization)) {
          String resp = productMgmtService.save(productDTO,"U");
          return resp.startsWith("S") ? new ResponseEntity<>(resp, HttpStatus.OK)
                                      : new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>("User not Authorized", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<ProductDetailsDTO>> findByName(@RequestHeader(value="Authorization", required=true) String authorization,
                                                              @PathVariable String name) {
        if(securityService.verifyUserAuthentication(authorization)) {
            List<ProductDetailsDTO> list = productMgmtService.findByName(name);
            return list.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                                  : new ResponseEntity<>(list, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/hello")
    String hello() {
        return "Hello World " + " !";

    }
}
