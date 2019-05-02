package com.garvey.property.controller;

import com.garvey.property.annotation.NeededAuthority;
import com.garvey.property.constant.Authority;
import com.garvey.property.model.AuthOperation;
import com.garvey.property.model.User;
import com.garvey.property.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author GarveyWong
 * @date 2019/4/29
 */
@Controller
@RequestMapping("/auth")
@NeededAuthority(authorities = Authority.BASIC_READ)
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/applicationList")
    @ResponseBody
    public List<AuthOperation> getAuthApplications(User user) {
        return authService.getAuthApplications(user.getCredentials());
    }

    @PostMapping("/cancellationList")
    @ResponseBody
    public List<AuthOperation> getAuthCancellations(User user) {
        return authService.getAuthCancellations(user.getCredentials());
    }

    @PostMapping("/apply")
    @ResponseBody
    @NeededAuthority(authorities = Authority.VOTE_MOTION)
    public String applyAuth(@RequestParam("auth") int auth, User user) {
        authService.applyAuth(user.getCredentials(), auth);
        return "200";
    }

    @PostMapping("/cancel")
    @ResponseBody
    @NeededAuthority(authorities = Authority.VOTE_MOTION)
    public String applyAuth(@RequestParam("auth") int auth, @RequestParam("targetAddr") String targetAddress,
                            User user) {
        authService.cancelAuth(user.getCredentials(), auth, targetAddress);
        return "200";
    }

    @PostMapping("/application/agree")
    @ResponseBody
    @NeededAuthority(authorities = Authority.VOTE_MOTION)
    public String agreeApplication(@RequestParam("idx") int idx, User user) {
        authService.agreeAuthApplication(user.getCredentials(), idx);
        return "200";
    }

    @PostMapping("/application/disagree")
    @ResponseBody
    @NeededAuthority(authorities = Authority.VOTE_MOTION)
    public String disagreeApplication(@RequestParam("idx") int idx, User user) {
        authService.disagreeAuthApplication(user.getCredentials(), idx);
        return "200";
    }

    @PostMapping("/cancellation/agree")
    @ResponseBody
    @NeededAuthority(authorities = Authority.VOTE_MOTION)
    public String agreeCancellation(@RequestParam("idx") int idx, User user) {
        authService.agreeAuthCancellation(user.getCredentials(), idx);
        return "200";
    }

    @PostMapping("/cancellation/disagree")
    @ResponseBody
    @NeededAuthority(authorities = Authority.VOTE_MOTION)
    public String disagreeCancellation(@RequestParam("idx") int idx, User user) {
        authService.disagreeAuthCancellation(user.getCredentials(), idx);
        return "200";
    }

    @PostMapping("/voteAttr/edit")
    @ResponseBody
    @NeededAuthority(authorities = Authority.SUPER)
    public String editVoteAttr(@RequestParam("settledMinCount") int settledMinCount,
                               @RequestParam("settledRatio") int settledRatio, User user) {
        authService.setSettledMinCount(settledMinCount, user.getCredentials());
        authService.setSettledRatio(settledRatio, user.getCredentials());
        return "200";
    }
}
