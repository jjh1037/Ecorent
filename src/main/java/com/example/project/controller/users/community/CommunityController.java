package com.example.project.controller.users.community;

import com.example.project.dto.user.CommentDto;
import com.example.project.dto.user.LoveDto;
import com.example.project.dto.user.MemberDto;
import com.example.project.dto.user.UsersPostDto;
import com.example.project.mappers.user.PostMapper;
import com.example.project.service.category.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/users/community")
public class CommunityController {

    @Autowired
    PostService postService;
    @Autowired
    PostMapper postMapper;

    @GetMapping("/listPost")
    public String getCommunity(@RequestParam(value = "page", defaultValue = "1")int page, @RequestParam(value = "searchType", defaultValue = "")String searchType, @RequestParam(value = "words", defaultValue = "")String words,Model model){
        model.addAttribute("board",postService.getPostList(page,searchType,words));
        model.addAttribute("page", postService.pageInfo(page, searchType, words));

        String searchQuery = postService.getSearch(searchType, words);
        model.addAttribute("searchCount",postMapper.getCountSearch(searchQuery));
        return "users/community/listPost";
    }

    @GetMapping("/post")
    public String getPost(HttpSession session){
        if(session.getAttribute("user")==null){
            return "redirect:/users/member/memberLogin/login";
        }
        return "users/community/post";
    }

    @PostMapping("/post")
    @ResponseBody
    public Map<String, Object> setPost(@ModelAttribute UsersPostDto postDto, HttpSession session){
        Map<String, Object> map = new HashMap<>();
        if(session.getAttribute("user")==null){
            map.put("result","login");
        }else{
            MemberDto user = (MemberDto) session.getAttribute("user");
            if(postDto!=null){
                postDto.setPostWriter(user.getMemberNickName());
                postMapper.updateMemberPoint(user.getMemberId());
                postMapper.setPost(postDto);
                map.put("result","good");
            }else{
                map.put("result","bad");
            }

        }
        return map;
    }

    @GetMapping("/viewPost")
    public String getViewPost(@RequestParam int id, Model model, HttpSession session){
        if(session.getAttribute("user")!=null){
            MemberDto user = (MemberDto) session.getAttribute("user");
            LoveDto love = new LoveDto();
            love.setMemberId(user.getMemberId());
            love.setPostNum(id);
            model.addAttribute("love",postMapper.checkLove(love));
        }
        UsersPostDto result = postMapper.viewPost(id);
        model.addAttribute("selectedPost", result);
        return "users/community/viewPost";
    }

    @PostMapping("/viewPost/deletePost")
    @ResponseBody
    public Map<String, Object> removePost(@RequestParam int postNum){
        Map<String, Object> map = new HashMap<>();
        postMapper.removePost(postNum);

        map.put("result", "success");

        return map;
    }

    @GetMapping("/editPost")
    public String getEditPost(@RequestParam int postNum, Model model){
        UsersPostDto result = postMapper.viewPost(postNum);
        model.addAttribute("selectedPost",result);
        return "users/community/editPost";
    }

    @PostMapping("/editPost")
    @ResponseBody
    public Map<String, Object> editPost(@ModelAttribute UsersPostDto pdto) {
        Map<String,Object> map = new HashMap<>();
        if(pdto!=null){
            postMapper.editPost(pdto);
            map.put("result","good");
        }else{
            map.put("result","failed");
        }
        return map;
    }

    @GetMapping("/viewPost/setLove")
    @ResponseBody
    public Map<String, Object> setLove(@ModelAttribute LoveDto loveDto){
        Map<String,Object> map = new HashMap<>();
        if(postMapper.checkCountLove(loveDto)>0){
            if(postMapper.checkLove(loveDto).getState()==0) {
                postMapper.updateTrueLove(loveDto);
                map.put("love","true");
            }else{
                postMapper.updateFalseLove(loveDto);
                map.put("love","false");
            }
        }else{
            postMapper.setLove(loveDto);
            map.put("love","true");
        }
        return map;
    }
    @PostMapping("/viewPost/comment")
    @ResponseBody
    public Map<String, Object> setComment(@ModelAttribute CommentDto cdto){
        postMapper.setComment(cdto);
        return Map.of("result","success");
    }

    @PostMapping("/viewPost")
    @ResponseBody
    public Map<String, Object> getComments(@RequestParam int postNum){
        Map<String, Object> map = new HashMap<>();

        if(postMapper.getComments(postNum).isEmpty()){
            map.put("result","zero");
        }else{
            map.put("result",postMapper.getComments(postNum));
        }
        return map;
    }

}
