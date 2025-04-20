package com.pfe.controller;

import com.pfe.dao.GetClient;
import com.pfe.dataBase.ConnexionDB;
import com.pfe.model.Client;
import com.pfe.model.Employe;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userType = req.getParameter("userType");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String login  = null;
        HttpSession session = req.getSession();
        if(userType.equals("client")){
            Client client ;
            client= GetClient.getClient(username, password);

            if(client!=null ){ // cette condition n est pas suffisant  ( voir la ligne 16 de (ligon.java ))
                System.out.println("Client logged in :) ");
                System.out.println("Client name : "+client.getNom());
                session.setAttribute("username", username);
                session.setAttribute("client", client);
                login = "yes";
                req.setAttribute("client", client);
                req.getRequestDispatcher("dashbordClient.jsp").forward(req, resp);
            }else {
                System.out.println("No client logged in :( ");
                login = "no";
                resp.sendRedirect("index.jsp");
            }

            session.setAttribute("login", login);


        }else if(userType.equals("employee")){
            resp.sendRedirect("loginEmp.jsp"); // pour redireger vers la page de login des employees

        }else
            session.invalidate();
    }
}
