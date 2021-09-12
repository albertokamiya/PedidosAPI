package br.com.app.myapi.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST")
//@CrossOrigin(origins = "*")
public class MyApiController {

}
