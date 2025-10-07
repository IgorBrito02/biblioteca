package com.igor.biblioteca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI bibliotecaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("📚 API Biblioteca – Sistema de Gestão")
                        .description(
                                "<div style='text-align:center; margin-bottom:30px;'>"
                                + "<img src='/images/logo-biblioteca.png' alt='Logo Biblioteca' style='width:120px; margin-bottom:10px;'/>"
                                + "<h1 style='color:#2E8B57; font-family:Arial, sans-serif;'>Bem-vindo à API Biblioteca</h1>"
                                + "<p style='color:#555; font-size:16px; max-width:700px; margin:auto;'>"
                                + "Gerencie autores, livros, exemplares, usuários e empréstimos com facilidade.<br>"
                                + "Abaixo estão as principais seções da API, com cores e ícones para melhor visualização."
                                + "</p></div>"

                                + "<div style='display:flex; justify-content:center; gap:20px; flex-wrap:wrap; margin-top:20px;'>"

                                + "<div style='background:#E6F4EA; padding:15px; border-radius:10px; box-shadow:0 2px 5px rgba(0,0,0,0.1); width:200px;'>"
                                + "<h3 style='color:#2E8B57;'>📖 Autores</h3>"
                                + "<p style='font-size:14px; color:#555;'>Gerencie cadastro de autores e informações detalhadas.</p></div>"

                                + "<div style='background:#FFF4E6; padding:15px; border-radius:10px; box-shadow:0 2px 5px rgba(0,0,0,0.1); width:200px;'>"
                                + "<h3 style='color:#FF8C00;'>📚 Livros</h3>"
                                + "<p style='font-size:14px; color:#555;'>Adicione, atualize e visualize livros disponíveis na biblioteca.</p></div>"

                                + "<div style='background:#E6F0FF; padding:15px; border-radius:10px; box-shadow:0 2px 5px rgba(0,0,0,0.1); width:200px;'>"
                                + "<h3 style='color:#1E90FF;'>📦 Exemplares</h3>"
                                + "<p style='font-size:14px; color:#555;'>Controle os exemplares de cada livro com facilidade.</p></div>"

                                + "<div style='background:#FFE6F0; padding:15px; border-radius:10px; box-shadow:0 2px 5px rgba(0,0,0,0.1); width:200px;'>"
                                + "<h3 style='color:#FF1493;'>👤 Usuários & Empréstimos</h3>"
                                + "<p style='font-size:14px; color:#555;'>Gerencie usuários e registre empréstimos de forma rápida.</p></div>"

                                + "</div>"
                        )
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Igor Pinheiro de Brito")
                                .email("igor_pbrito@hotmail.com")
                                .url("https://github.com/IgorBrito02/biblioteca"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                );
    }
}
