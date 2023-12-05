/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesion;

import javax.servlet.http.HttpSession;

/**
 *
 * @author carlo
 */
public class Sesion {
    public static void crearsesion(HttpSession session, int codi, String logi, String nombre) {
        session.setAttribute("logueado", "1");
        session.setAttribute("codi", codi);
        session.setAttribute("logi", logi);
        session.setAttribute("nombre", nombre);

    }
    public static boolean sesionvalida(HttpSession session) {
        String logueado=(String)session.getAttribute("logueado");
        return logueado.equals("1");
    }
    public static void cerrarsesion(HttpSession session){
        session.removeAttribute("logueado");
        session.removeAttribute("codi");
        session.removeAttribute("logi");
        session.removeAttribute("nombre");
        session.invalidate();
    }
    public static int getCodi(HttpSession session){
        Object ocodigo=session.getAttribute("codi");
        return Integer.parseInt(ocodigo.toString());
    }
     public static String getLogi(HttpSession session){
        Object ologi=session.getAttribute("logi");
        return ologi.toString();
    }
}
