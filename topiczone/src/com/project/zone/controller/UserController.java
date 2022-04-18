package com.project.zone.controller;


import com.project.myssm.util.Tools;
import com.project.zone.bean.*;
import com.project.zone.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description 控制用户信息
 * @Author wk
 * @Date 2022/3/22 11:21
 * @Version 1.0
 */
public class UserController {

    private UserBasicService userBasicService = null;
    private TopicService topicService = null;
    private UserDetailService userDetailService = null;
    private ReplyService replyService = null;
    private HostReplyService hostReplyService = null;


    /**
     * @author wk
     * @Description 进入注册功能页面
     * @Date 20:43 2022/4/4
     * @Param
     * @Return
     */

    public String intoRegister(HttpSession session) {
        session.setAttribute("registerError", false);
        return "register";
    }

    /**
     * @author wk
     * @Description 注册功能
     * @Date 22:16 2022/3/23
     * @Param
     * @Return
     */

    public String register(String loginId, String nickName, String password, String headImage, String mode, HttpSession session) throws SQLException {
        UserBasic user = userBasicService.getUserBasic(loginId, password);
        if (user == null) {
            UserBasic userBasic = new UserBasic(loginId, nickName, password, headImage);
            int register = userBasicService.addUserBasic(userBasic);
            if (register > 0) {
                //// 为用户详情表设置对应的id
                UserBasic userBasic1 = userBasicService.getUserBasic(loginId, password);
                userDetailService.addUserDetailId(userBasic1.getId());
                return "redirect:user.do?choice=login";
            }
        } else {
            if (Tools.isNotEmpty(mode) && "register".equals(mode)) {
                session.setAttribute("registerError", true);
            } else {
                session.setAttribute("registerError", false);
            }
        }
        return "register";
    }

    /**
     * @author wk
     * @Description 进入登录页面
     * @Date 22:56 2022/4/4
     * @Param
     * @Return
     */

    public String intoLogin(HttpSession session) {
        session.setAttribute("loginError", false);
        return "login";
    }


    /**
     * @author wk
     * @Description 登录功能
     * @Date 22:16 2022/3/23
     * @Param
     * @Return
     */

    public String login(String loginId, String password, String mode, HttpSession session) throws SQLException {
        UserBasic userBasic = userBasicService.getUserBasic(loginId, password);
        if (userBasic != null) {
            return "redirect:user.do?choice=index&userBasicId=" + userBasic.getId();
        } else {
            if (Tools.isNotEmpty(mode) && "login".equals(mode)) {
                session.setAttribute("loginError", true);
            } else {
                session.setAttribute("loginError", false);
            }
            return "login";
        }
    }


    /**
     * @author wk
     * @Description 注销账户
     * @Date 21:11 2022/4/3
     * @Param
     * @Return
     */

    public String shutAccount(Integer userBasicId) throws SQLException, ParseException {

        UserBasic userBasic = userBasicService.getUserBasicById(userBasicId);

        // 查找是否存在好友
        List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
        // 查找是否是别人的好友
        List<UserBasic> userList = userBasicService.getUserListByFriendId(userBasicId);
        List<HostReply> hostReplyList = hostReplyService.getHostReplyByUserId(userBasicId);
        List<Reply> replyList = replyService.getReplyListByUserId(userBasicId);
        List<Topic> topicList = topicService.getTopicList(userBasic);
        UserDetail userDetail = userDetailService.getUserDetailById(userBasicId);

        if (friendList != null) {
            userBasicService.deleteFriendByUserId(userBasicId);
        }
        if (userList != null) {
            userBasicService.deleteFriend(userBasicId);
        }

        if (hostReplyList != null) {
            hostReplyService.deleteHostReplyByUserId(userBasicId);
        }
        if (replyList != null) {
            List<HostReply> hostReplyList1 = null;
            for(int i = 0;i < replyList.size();i++){
                hostReplyList1 = hostReplyService.getHostReplyList(replyList.get(i).getId());
                if(hostReplyList1 != null){
                    hostReplyService.deleteHostReplyByReplyId(replyList.get(i).getId());
                }
            }
            replyService.deleteReplyByUserId(userBasicId);
        }
        if (topicList != null) {
            List<Reply> replyList1 = null;
           for(int i = 0;i < topicList.size();i++){
                replyList1 = replyService.getReplyList(topicList.get(i));
                if(replyList1 != null){
                    replyService.deleteReplyByTopicId(topicList.get(i).getId());
                }
           }

            topicService.deleteTopicByUserId(userBasicId);
        }
        if (userDetail != null) {
            userDetailService.deleteUserDetail(userBasicId);
        }
        Integer deleteUserBasic = userBasicService.deleteUserBasicByUserId(userBasicId);
        if (deleteUserBasic > 0) {
            return "redirect:user.do?choice=login";
        }
        return "error";
    }

    /**
     * @author wk
     * @Description 首页
     * @Date 16:02 2022/4/1
     * @Param
     * @Return
     */

    public String index(Integer userBasicId, Integer pageNumber, HttpServletRequest request, HttpSession session) throws SQLException {

        UserBasic userBasic = userBasicService.getUserBasicById(userBasicId);

        if (pageNumber == null) {
            pageNumber = 1;
        }
        // 获取总记录条数
        Long topicCount = topicService.getTopicCount(userBasic);
        // 总页数
        Long pageCount = (topicCount + 5 - 1) / 5;
        // 将当前页码 和 总页数 设置到作用域
        session.setAttribute("pageNumber", pageNumber);
        session.setAttribute("pageCount", pageCount);

        // 获取好友列表
        List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
        // 获取日志列表
        List<Topic> topicList = topicService.getTopicListByPage(userBasic, pageNumber);

        userBasic.setFriendList(friendList);
        userBasic.setTopicList(topicList);

        // userBasic这个属性保存的是当前登录者信息
        request.getSession().setAttribute("userBasic", userBasic);
        // friend这个属性保存的是当前查看的好友信息
        request.getSession().setAttribute("friend", userBasic);

        return "index";
    }


    /**
     * @author wk
     * @Description 进入找回密码页面
     * @Date 20:47 2022/4/4
     * @Param
     * @Return
     */
    public String intoRecoverPassword(HttpSession session) {
        session.setAttribute("recoverPasswordError", false);
        return "recoverPassword";
    }


    /**
     * @author wk
     * @Description 进入找回密码的结果页面
     * @Date 22:25 2022/3/31
     * @Param
     * @Return
     */

    public String recover(Integer userBasicId, HttpSession session) throws SQLException {
        UserBasic userBasic = userBasicService.getUserBasicById(userBasicId);
        if (userBasic != null) {
            session.setAttribute("recoverPassword", userBasic.getPassword());
            return "result";
        }
        return "error";
    }

    /**
     * @author wk
     * @Description 找回密码
     * @Date 22:13 2022/3/31
     * @Param
     * @Return
     */

    public String recoverPassword(String phone, String mode, HttpSession session) throws SQLException {
        Integer userBasicId = userDetailService.getUserDetailIdByPhone(phone);
        if (userBasicId != null) {
            return "redirect:user.do?choice=recover&userBasicId=" + userBasicId;
        } else {
            if (Tools.isNotEmpty(mode) && "recoverPassword".equals(mode)) {
                session.setAttribute("recoverPasswordError", true);
            } else {
                session.setAttribute("recoverPasswordError", false);
            }
            return "recoverPassword";
        }
    }


    /**
     * @author wk
     * @Description 好友列表
     * @Date 21:14 2022/3/25
     * @Param
     * @Return
     */

    public String friend(Integer id, Integer pageNumber, HttpServletRequest request, HttpSession session) throws SQLException {
        // 根据id获取指定用户的信息(获取好友信息)
        UserBasic currentFriend = userBasicService.getUserBasicById(id);
        // 获取好友列表信息
        List<UserBasic> friendList = userBasicService.getFriendList(currentFriend);
        // 获取好友的日志列表信息
        if (pageNumber == null) {
            pageNumber = 1;
        }
        // 获取总记录条数
        Long topicCount = topicService.getTopicCount(currentFriend);
        // 总页数
        Long pageCount = (topicCount + 5 - 1) / 5;
        System.out.println(currentFriend.getNickName() + "的总页数：" + pageCount);

        session.setAttribute("pageNumber", pageNumber);
        session.setAttribute("pageCount", pageCount);

        List<Topic> topicList = topicService.getTopicListByPage(currentFriend, pageNumber);
        if (currentFriend != null && topicList != null) {
            // 将好友信息放入UserBasic类中的好友列表中
            currentFriend.setFriendList(friendList);
            // 将好友日志列表信息放入UserBasic类中的日志列表中
            currentFriend.setTopicList(topicList);
            request.getSession().setAttribute("friend", currentFriend);
            return "index";
        } else {
            return "error";
        }
    }


    /**
     * @author wk
     * @Description 进入个人详情
     * @Date 13:29 2022/3/31
     * @Param
     * @Return
     */

    public String personal(Integer userBasicId, HttpSession session) throws SQLException {
        Map<String, Boolean> errorMap = new HashMap<>();
        session.setAttribute("errorMap", errorMap);
        UserBasic userBasic = userBasicService.getUserBasicById(userBasicId);
        UserDetail userDetail = userDetailService.getUserDetailById(userBasicId);
        if (userBasic != null) {
            session.setAttribute("userBasic", userBasic);
        }
        if (userDetail != null) {
            session.setAttribute("userDetail", userDetail);
        }
        return "personal";
    }


    /**
     * @author wk
     * @Description 更新个人详情
     * @Date 22:28 2022/3/30
     * @Param
     * @Return
     */

    public String updatePersonal(Integer userBasicId, String name, String sex, String email, String star, String birth, String phone, String cardId, String mode, HttpSession session) throws ParseException, SQLException {
        Integer userBasicId1 = userDetailService.getUserDetailIdByPhone(phone);
        Integer userBasicId2 = userDetailService.getUserDetailIdByEmail(email);
        Integer userBasicId3 = userDetailService.getUserDetailIdByCardId(cardId);
        Map<String, Boolean> errorMap = new HashMap<>();
        if (userBasicId1 != null && userBasicId1 != userBasicId) {
            if (Tools.isNotEmpty(mode) && "personal".equals(mode)) {
                errorMap.put("phoneError", true);
            } else {
                errorMap.put("phoneError", false);
            }
        }
        if (userBasicId2 != null && userBasicId2 != userBasicId) {
            if (Tools.isNotEmpty(mode) && "personal".equals(mode)) {
                errorMap.put("emailError", true);
            } else {
                errorMap.put("emailError", false);
            }
        }
        if (userBasicId3 != null && userBasicId3 != userBasicId) {
            if (Tools.isNotEmpty(mode) && "personal".equals(mode)) {
                errorMap.put("cardIdError", true);
            } else {
                errorMap.put("cardIdError", false);
            }
        }
        if (!errorMap.isEmpty()) {
            System.out.println("我被调用了");
            session.setAttribute("errorMap", errorMap);
            return "personal";
        }

        // 解析生日
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birth1 = sdf.parse(birth);
        java.sql.Date birth2 = new java.sql.Date(birth1.getTime());
        UserDetail userDetail = new UserDetail(userBasicId, name, sex, cardId, phone, email, birth2, star);
        // 更新信息
        Integer addUserDetail = userDetailService.updateUserDetail(userDetail);
        if (addUserDetail > 0) {
            session.setAttribute("userDetail", userDetail);
            return "redirect:user.do?choice=personal&userBasicId=" + userBasicId;
        }
        return "error";
    }

    /**
     * @author wk
     * @Description 好友管理
     * @Date 22:16 2022/4/1
     * @Param
     * @Return
     */

    public String friendManage(String mode, String keyword, Integer userBasicId, Integer pageNumber, HttpSession session) throws SQLException {
        // 展示好友列表或搜索结果列表的标识，false：展示好友列表，true：展示搜索结果列表
        boolean flag = false;
        // 添加好友标识,false：不能添加，true：可以添加
        boolean isAddFriend = true;

        if (pageNumber == null) {
            pageNumber = 1;
        }

        // 获取当前用户的基础信息
        UserBasic userBasic = userBasicService.getUserBasicById(userBasicId);
        // 获取当前用户的好友列表（全体）
        List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
        // 获取当前用户的好友列表（分页）
        List<UserBasic> friendList1 = userBasicService.getFriendListByPageNumber(userBasic, pageNumber);

        // 如果 mode 等于 'search' 则是通过搜索框发来请求
        if (Tools.isNotEmpty(mode) && "search".equals(mode)) {
            flag = true;
            if (Tools.isEmpty(keyword)) {
                keyword = "";
                flag = false;
            }
            session.setAttribute("keyword", keyword);
            session.setAttribute("flag", flag);

        } else {
            // 不是通过搜索框发来的请求
            flag = false;
            session.setAttribute("flag", false);
            Object keyword1 = session.getAttribute("keyword");
            if (keyword1 != null) {
                keyword = (String) keyword1;
            } else {
                keyword = "";
            }
        }
        // 查询搜索框请求的内容
        if (flag) {
            // 获取搜索到的用户
            List<UserBasic> userBasicList = userBasicService.getUserBasicByKeyWord(keyword, pageNumber);
            // 将获取到的全体用户，和当前用户的好友进行比对
            if (!userBasicList.isEmpty() && !friendList.isEmpty()) {
                for (int i = 0; i < userBasicList.size(); i++) {
                    if (userBasicList.get(i).getLoginId().equals(userBasic.getLoginId())) {
                        // 如果搜索的用户是自己的话，不能添加自己为好友
                        isAddFriend = false;
                    } else {
                        for (int j = 0; j < friendList.size(); j++) {
                            if (userBasicList.get(i).getLoginId().equals(friendList.get(j).getLoginId())) {
                                isAddFriend = false;
                                break;
                            } else {
                                isAddFriend = true;
                            }
                        }
                    }
                    userBasicList.get(i).setAddFriend(isAddFriend);
                }
            } else if (!userBasicList.isEmpty() && friendList.isEmpty()) {
                for (int i = 0; i < userBasicList.size(); i++) {
                    if (userBasicList.get(i).getLoginId().equals(userBasic.getLoginId())) {
                        // 如果搜索的用户是自己的话，不能添加自己为好友
                        isAddFriend = false;
                    } else {
                        isAddFriend = true;
                    }
                    userBasicList.get(i).setAddFriend(isAddFriend);
                }
            }
            // 获取用户总记录数
            Long userBasicCount = userBasicService.getUserBasicCountByKeyWord(keyword);
            // 总页数
            Long userPageCount = (userBasicCount + 5 - 1) / 5;

            // 保存查询到的用户信息
            session.setAttribute("userBasicList", userBasicList);
            // 保存页码信息
            session.setAttribute("userPageNumber", pageNumber);
            // 保存总页数
            session.setAttribute("userPageCount", userPageCount);

        } else {
            session.setAttribute("userBasic", userBasic);
            session.setAttribute("friendList", friendList1);

            // 获取好友总记录数
            Long friendCount = userBasicService.getFriendCount(userBasic);
            // 获取总页数
            Long friendPageCount = (friendCount + 5 - 1) / 5;

            session.setAttribute("friendPageNumber", pageNumber);
            session.setAttribute("friendPageCount", friendPageCount);
        }
        return "friendManage";
    }

    /**
     * @author wk
     * @Description 添加好友
     * @Date 14:50 2022/4/2
     * @Param
     * @Return
     */

    public String addFriend(Integer userBasicId, Integer friendId) throws SQLException {
        UserBasic userBasic = userBasicService.getUserBasicById(userBasicId);
        List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
        // 判断要关注的用户，是否已经被关注
        boolean flag = true;
        for (int i = 0; i < friendList.size(); i++) {
            if (friendList.get(i).getId() == friendId) {
                flag = false;
                break;
            }
        }
        if (flag) {
            Integer addFriend = userBasicService.addFriend(userBasicId, friendId);
            if (addFriend > 0) {
                return "redirect:user.do?choice=friendManage&userBasicId=" + userBasicId;
            }
        }
        return "error";
    }

    /**
     * @author wk
     * @Description 删除好友
     * @Date 15:11 2022/4/2
     * @Param
     * @Return
     */

    public String deleteFriend(Integer friendId, Integer userBasicId) throws SQLException {
        Integer deleteFriend = userBasicService.deleteFriend(friendId);
        if (deleteFriend > 0) {
            return "redirect:user.do?choice=friendManage&userBasicId=" + userBasicId;
        }
        return "error";
    }
}
