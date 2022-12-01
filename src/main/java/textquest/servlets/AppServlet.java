package textquest.servlets;

import textquest.enums.MessageNumbers;
import textquest.service.MessageService;
import textquest.service.PropertiesLoader;
import textquest.service.StoryBuilder;
import textquest.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/quest")
public class AppServlet extends HttpServlet {
    private UserService userService;
    private MessageService messageService;
    private StoryBuilder storyBuilder;
    private MessageNumbers messageNumbers;
    private PropertiesLoader propertiesLoader = new PropertiesLoader();

    @Override
    public void init() {
        propertiesLoader.loadProperties();
        storyBuilder = new StoryBuilder();
        storyBuilder.buildStory(messageNumbers);
        userService = new UserService();
        messageService = new MessageService();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("messageId"));
        String message = messageService.getMessageById(id, storyBuilder);
        String linkYes = messageService.getLink("yes", id, storyBuilder);
        String linkNo = messageService.getLink("no", id, storyBuilder);
        String hidden = "";
        int pictureId = messageService.getIdOfPicture(id, storyBuilder);
        if (id == 5) {
            hidden = "hidden";
        }
        req.setAttribute("pictureId", pictureId);
        req.setAttribute("message", message);
        req.setAttribute("linkYes", linkYes);
        req.setAttribute("linkNo", linkNo);
        req.setAttribute("hidden", hidden);
        getServletContext()
                .getRequestDispatcher("/WEB-INF/quest.jsp")
                .forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        if(name == "") {
            getServletContext()
                    .getRequestDispatcher("/index.jsp")
                    .forward(req, resp);
        }
        HttpSession session = req.getSession();
        session.setAttribute("name", name);
        userService.checkUser(name, session);
        int id = 1;
        String message = messageService.getMessageById(id, storyBuilder);
        String linkYes = messageService.getLink("yes", id, storyBuilder);
        String linkNo = messageService.getLink("no", id, storyBuilder);
        int pictureId = messageService.getIdOfPicture(id, storyBuilder);
        req.setAttribute("message", message);
        req.setAttribute("linkYes", linkYes);
        req.setAttribute("linkNo", linkNo);
        req.setAttribute("pictureId", pictureId);
        getServletContext()
                .getRequestDispatcher("/WEB-INF/quest.jsp")
                .forward(req, resp);
    }
}
