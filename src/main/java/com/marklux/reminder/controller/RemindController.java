package com.marklux.reminder.controller;

import com.marklux.reminder.common.DateTransfer;
import com.marklux.reminder.domain.Remind;
import com.marklux.reminder.domain.User;
import com.marklux.reminder.service.RemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by lumin on 18/6/23.
 */
@Controller
public class RemindController {
    @Autowired
    private RemindService remindService;
    @Autowired
    private DateTransfer dateTransfer;

    @GetMapping("/home")
    public String getPanel(Model model, HttpServletRequest request)
    {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            return "redirect:login";
        }
        model.addAttribute("user", user);

        Map<String, List<Remind>> remindMap =
                remindService.getRemindsByUserId(user.getId());

        model.addAttribute("pinnedReminds", remindMap.get("pinned"));
        model.addAttribute("unpinnedReminds", remindMap.get("unpinned"));
        model.addAttribute("fmt", dateTransfer);

        return "app/panel";
    }

    @PostMapping("remind/add")
    public String addRemind(@ModelAttribute Remind remind, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        remind.setUserId(user.getId());
        this.remindService.createRemind(remind);
        return "redirect:/home";
    }

    @GetMapping("remind/delete")
    public String deleteRemind(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        long remindId = Long.valueOf(request.getParameter("remind_id"));
        this.remindService.deleteRemind(remindId);
        return "redirect:/home";
    }

    @GetMapping("remind/pin")
    public String pinRemind(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        long remindId = Long.valueOf(request.getParameter("remind_id"));
        this.remindService.updateRemindPinStatus(remindId, new Short("1"));
        return "redirect:/home";
    }

    @GetMapping("remind/unpin")
    public String unPinRemind(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        long remindId = Long.valueOf(request.getParameter("remind_id"));
        this.remindService.updateRemindPinStatus(remindId, new Short("0"));
        return "redirect:/home";
    }
}
