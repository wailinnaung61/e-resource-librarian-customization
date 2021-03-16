/*
 * package controllers;
 * 
 * import java.util.List; import javax.servlet.http.HttpServletRequest; import
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
 * beans.UserBean; import dao.UserDao;
 * 
 * @Controller public class CollectionController {
 * 
 * @Autowired UserDao dao;
 * 
 * // For Add Collection
 * 
 * @RequestMapping("/Addcollection") public String AddRole(Model m,
 * HttpServletRequest req, HttpServletResponse res) { m.addAttribute("command",
 * new UserBean()); return "Addcollection"; }
 * 
 * // For Clicking Create Collection Button
 * 
 * @RequestMapping(value = "/savecollection", method = RequestMethod.POST)
 * public String saverole(@ModelAttribute("user") UserBean user,
 * RedirectAttributes redir) { int count =
 * dao.collectionbycollection(user.getCollection());
 * 
 * if (count > 0) { redir.addFlashAttribute("alert",
 * "Collection name already exist.Please Use another Collection Name!"); return
 * "redirect:/Addcollection"; } else { dao.savecollection(user); //
 * redir.addFlashAttribute("successful", "Role Created Successful!"); return
 * "redirect:/Viewcollection"; } }
 * 
 * // For View Collection
 * 
 * @RequestMapping("/Viewcollection") public String viewcollection(Model m,
 * HttpServletRequest req, HttpServletResponse res) { List<UserBean> list =
 * dao.getCollections(); m.addAttribute("list", list); return "Viewcollection";
 * }
 * 
 * // For Clicking deleteRole in Viewuser
 * 
 * @RequestMapping(value = "/deletecollection/{collectionid}", method =
 * RequestMethod.GET) public String deleterole(@PathVariable int collectionid) {
 * dao.deletecollection(collectionid); return "redirect:/Viewcollection"; }
 * 
 * // For Clicking Editcollection in View collection
 * 
 * @RequestMapping(value = "/editcollection/{collectionid}") public String
 * editrole(@PathVariable int collectionid, Model m, HttpServletRequest req,
 * HttpServletResponse re) { UserBean user =
 * dao.getCollectionById(collectionid); m.addAttribute("command", user); return
 * "collectionedit"; }
 * 
 * // For EditSave Role Button
 * 
 * @RequestMapping(value = "/editsavecollection", method = RequestMethod.POST)
 * public String editsaverole(@ModelAttribute("user") UserBean user) {
 * dao.updatecollection(user); return "redirect:/Viewcollection"; } }
 */