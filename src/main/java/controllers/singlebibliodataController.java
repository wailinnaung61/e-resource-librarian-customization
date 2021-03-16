/*
 * package controllers;
 * 
 * import java.util.List;
 * 
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse; import
 * javax.servlet.http.HttpSession; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.servlet.mvc.support.RedirectAttributes; import
 * beans.UserBean; import beans.bibliosingledata; import dao.UserDao;
 * 
 * @Controller public class singlebibliodataController {
 * 
 * @Autowired UserDao dao;
 * 
 * // For Add Bibliosingledata
 * 
 * @RequestMapping("/importsinglebibliodata") public String
 * Addbibliosingledata(Model m, HttpServletRequest req, HttpServletResponse res)
 * { List<UserBean> listforitemtype = dao.getitemtypesnameandcode();
 * List<UserBean> list = dao.getCollectionname();
 * 
 * m.addAttribute("command", new bibliosingledata()); m.addAttribute("list1",
 * listforitemtype); m.addAttribute("list", list); return
 * "importsinglebibliodata"; }
 * 
 * // For Clicking Create Collection Button
 * 
 * @RequestMapping(value = "/savebibliosingledata", method = RequestMethod.POST)
 * public String saverole(@ModelAttribute("bibliosingledata") bibliosingledata
 * user, RedirectAttributes redir) {
 * 
 * int countitemnumber = dao.checkitemnumber(user); if (countitemnumber > 0) {
 * redir.addFlashAttribute("alert",
 * "Duplicate Itemnumber.Please! Check Your Biblio Data"); return
 * "redirect:/importsinglebibliodata"; } else { dao.savesingledata(user); return
 * "redirect:/bibliolist"; } }
 * 
 * // For View updatebibliolist
 * 
 * @RequestMapping("/updatebibliolist") public String viewitemtype(Model m,
 * HttpServletRequest req, HttpServletResponse res) { List<bibliosingledata>
 * list = dao.getsinglebibliodata(); m.addAttribute("list", list); return
 * "updatebibliolist"; }
 * 
 * // For Clicking deleteRole in Viewuser
 * 
 * @RequestMapping(value =
 * "/deleteupdatebibliolist/{itemnumber}/{biblioitemnumber}/{biblionumber}",
 * method = RequestMethod.GET) public String deleterole(@PathVariable int
 * itemnumber, @PathVariable int biblioitemnumber,
 * 
 * @PathVariable int biblionumber) {
 * dao.deleteupdatebibliolistitems(itemnumber); return
 * "redirect:/updatebibliolist"; }
 * 
 * // For Clicking Editlist in View Role
 * 
 * @RequestMapping(value = "/editupdatebibliolist/{itemnumber}") public String
 * editrole(@PathVariable int itemnumber, Model m, HttpServletRequest req,
 * HttpServletResponse re) { bibliosingledata user =
 * dao.getRolebibliolist(itemnumber); List<UserBean> listforitemtype =
 * dao.getitemtypesnameandcode(); List<UserBean> list = dao.getCollectionname();
 * m.addAttribute("list1", listforitemtype); m.addAttribute("list", list);
 * m.addAttribute("command", user);
 * 
 * return "updatebibliolistedit"; }
 * 
 * @RequestMapping(value = "/editsavesinglebibliolist", method =
 * RequestMethod.POST) public String editsaverole(@ModelAttribute("user")
 * bibliosingledata user) { dao.updatesinglebibliodata(user); return
 * "redirect:/updatebibliolist"; }
 * 
 * }
 */