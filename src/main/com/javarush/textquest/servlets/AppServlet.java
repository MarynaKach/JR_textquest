package main.com.javarush.textquest.servlets;

import main.com.javarush.textquest.enteties.Answer;
import main.com.javarush.textquest.enteties.Message;
import main.com.javarush.textquest.enteties.User;
import main.com.javarush.textquest.service.MessageService;
import main.com.javarush.textquest.service.StoryBuilder;
import main.com.javarush.textquest.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

@WebServlet( "/quest")
public class AppServlet extends HttpServlet {
    private Set<Message> set;
    private UserService userService;
    private Map<String, User> allUserList;
    MessageService messageService;
    StoryBuilder storyBuilder;
    Set<Message> messages;


    @Override
    public void init() {
        storyBuilder = new StoryBuilder();
        storyBuilder.buildStory();
        messages = storyBuilder.getMessages();
        userService = new UserService();
        allUserList = userService.getAllUsersList();
        messageService = new MessageService();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("messageId"));
        String message = messageService.getMessageById(id, storyBuilder);
        String linkYes = messageService.getLink("yes", id, storyBuilder);
        String linkNo = messageService.getLink("no", id, storyBuilder);
        String hidden = "";
        int pictureId = messageService.getPictureById(id, storyBuilder);
        if (id == 5) {
            hidden = "hidden";
        }
       /* int nextIdYes;
        int nextIdNo;
        if (id == 4 || id == 0) {
            linkYes = "index.jsp";
            linkNo = "/quest?messageId=5";
            hidden= "";
        } else if (id ==5 ) {
            hidden= "hidden";
        }else {
             nextIdYes = getNextId(id, "yes");
             nextIdNo = getNextId(id, "no");
            linkYes = "/quest?messageId=" + nextIdYes;
            linkNo = "/quest?messageId=" + nextIdNo;
            hidden= "";
        }*/
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
        String name = req.getParameter("name"); /*getNameFromRequest(req);*/
        HttpSession session = req.getSession();
        //User user;
        session.setAttribute("name", name);
        userService.checkUser(name, session);
       /* if (!userService.isUserExist(name)) {
            user = User.builder()
                    .name(name)
                    .numberOfGames(1)
                    .build();
            userService.saveUser(name, user);
            session.setAttribute("numberOfGames",  user.getNumberOfGames());

        } else {
            user = allUserList.get(name);
            user.setNumberOfGames(user.getNumberOfGames() + 1);
            session.setAttribute("numberOfGames", user.getNumberOfGames());
        }*/
        //session.setAttribute("id", "1");
        int id = 1;
        String message = messageService.getMessageById(id, storyBuilder);
        /*//int nextIdYes = getNextId(id, "yes");
        //int nextIdNo = getNextId(id, "no");
        String linkYes = "/quest?id=" + nextIdYes;
        String linkNo = "/quest?id=" + nextIdNo;*/
        String linkYes = messageService.getLink("yes", id, storyBuilder);
        String linkNo = messageService.getLink("no", id, storyBuilder);
        req.setAttribute("message", message);
        req.setAttribute("linkYes", linkYes);
        req.setAttribute("linkNo", linkNo);
        getServletContext()
                .getRequestDispatcher("/WEB-INF/quest.jsp")
                .forward(req, resp);
    }

   /* private int getMessageId(HttpServletRequest request) {
        int messageId = Integer.parseInt(request.getParameter("messageId"));
        //boolean isNumeric = message.chars().allMatch(Character::isDigit);
        return messageId; *//*isNumeric ? Integer.parseInt(message) : 0;*//*
    }*/
   /* private String getMessageById(int id) {
        String textMessage = null;
        for (Message message : messages) {
            if (message.getId() == id) {
                textMessage = message.getTextMessage();
            }
        }
        return textMessage;
    }*/
   /* private int getNextId (int id, String textAnswer) {
        Message message = null;

        for (Message message1 : messages) {
            if(message1.getId() == id) {
                message = message1;
            }
        }
        Answer answer = null;
        for (Answer answer1 : message.getSet()) {
            if (textAnswer.equalsIgnoreCase(answer1.getAnswerText())) {
                answer = answer1;
            }
        }
        return  answer.getNextMessageId();
    }*/

   /* private String getNameFromRequest(HttpServletRequest req) {
        String name = req.getParameter("name");
        return name;
    }*/
   /* public void htmlOutput(HttpServletResponse resp, String name, String message, int messageId, int numberOfGames){
        resp.setContentType("text/html");
        PrintWriter out;
        try {
            out = resp.getWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        out.println("<html><head>");
        out.println("<link href=\"static/main.css\" rel=\"stylesheet\">");
        out.println("</head><body>");
        out.println("<h1>" + name + " " + message + "</h1>");
        int nextIdYes = getNextId(messageId, "yes");
        int nextIdNo = getNextId(messageId, "no");
        if (messageId == 4 || messageId == 0) {
            out.println("<div><button onclick=\"window.location='/index.jsp'\">YES</button>");
            out.println("<button onclick=\"window.location='/quest?message=" + nextIdNo + "'\">NO</button>");
        } else {
            out.println("<div><button onclick=\"window.location='/quest?message=" + nextIdYes + "'\">YES</button>");
            out.println("<button onclick=\"window.location='/quest?message=" + nextIdNo + "'\">NO</button>");
        }
        out.println("</div>");
        out.println("<h3>" + "Name: " + name + "</h1>");
        out.println("<h4>" + "Number Of Games: " + numberOfGames + "</h2></body></html>");
    }*/
}
