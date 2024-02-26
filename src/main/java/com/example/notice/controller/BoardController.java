package com.example.notice.controller;


import com.example.notice.domain.Board;
import com.example.notice.domain.BoardDetails;
import com.example.notice.domain.file.FileRequest;
import com.example.notice.service.BoardService;
import com.example.notice.service.FIleService;
import com.example.notice.service.ReplyService;
import com.example.notice.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping(value = "/board")
@RequiredArgsConstructor // 추가1
public class BoardController {

    private final BoardService service;
    private final FIleService fileService;
    private final ReplyService replyService;
    private final FileUtils fileUtils;

    //    게시글 작성 페이지 이동
    @GetMapping("/writePage")
    public String BoardWritePage() {
        return "BoardWrite";
    }

    // 게시글 작성
    @PostMapping("/write")
    public String write(Board board, @RequestParam("files") List<MultipartFile> files) {
        service.write(board);
        int id = board.getBoard_Num();
        List<FileRequest> uploadedFiles = fileUtils.uploadFiles(files);
        fileService.saveFiles(id, uploadedFiles);
        return "redirect:/home";
    }

    // 게시글 상세 페이지 이동
    @GetMapping("/details/{boardNum}/{num}")
    public String details(@PathVariable int boardNum, @PathVariable int num,Model model) {
        BoardDetails boardDetails;
        if(num == 0){
            boardDetails = service.getBoard(boardNum);
        }else{
            boardDetails = service.getBoardtest(boardNum, num);
        }
        model.addAttribute("view", boardDetails);
        model.addAttribute("list", fileService.fileList(boardNum));
        model.addAttribute("reply",replyService.getList(boardNum));

        return "NoticeDetails";
    }

    // 게시글 수정 페이지 이동
    @GetMapping("/boardUpdatePage/{boardNum}")
    public String boardUpdate(@PathVariable int boardNum, Model model) {
        BoardDetails boardDetails = service.getUpdateBoard(boardNum);
        System.out.println(boardDetails.getNum());
        model.addAttribute("list", fileService.fileList(boardNum));
        model.addAttribute("view", boardDetails);
        return "BoardUpdate";
    }

    // 게시글 수정
    @PostMapping("/boardUpdatePage/boardUpdate")
    public String boardUpdate(@RequestParam("boardNum") int boardNum, @RequestParam("boardTitle") String boardTitle, @RequestParam("boardContent") String boardContent,@RequestParam("files") List<MultipartFile> files) {
        service.boardUpdate(boardNum, boardTitle, boardContent);
        List<FileRequest> uploadedFiles = fileUtils.uploadFiles(files);
        fileService.saveFiles(boardNum, uploadedFiles);
        return "redirect:/home";
    }

    // 게시글 삭제
    @PostMapping("/boardDelete")
    public String boardDelete(@RequestParam("boardNum") int boardNum) {
        service.boardDelete(boardNum);
        return "redirect:/home";
    }


    //    답글 작성 페이지
    @GetMapping("/comment/{boardNum}")
    public String commentPage(@PathVariable int boardNum, Model model){
        model.addAttribute("view", service.getBoard(boardNum));
        return "CommentWrite";
    }
//    답글 작성
    @PostMapping("/commentWrite")
    public String commentWrite(Board board, @RequestParam("files") List<MultipartFile> files,@RequestParam("board_Num") int boardNum){
        int id = board.getBoard_Num();
        List<FileRequest> uploadedFiles = fileUtils.uploadFiles(files);
        BoardDetails parent = service.getBoard(boardNum);
        service.commentUpdate(board, parent);
        service.commentWrite(board,parent);
        fileService.saveFiles(id, uploadedFiles);
        return "redirect:/home";
    }



    private static final String FILE_DIRECTORY = "C:\\noticeFile";
    @GetMapping("/fileDownload/{fileName}")
    public ResponseEntity<FileSystemResource> downloadFile(@PathVariable String fileName) throws IOException {
        // Load file as Resource
        Path filePath = Paths.get(FILE_DIRECTORY).resolve(fileName).normalize();
        FileSystemResource resource = new FileSystemResource(filePath);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = Files.probeContentType(filePath);
        } catch (IOException ex) {
            ex.printStackTrace(); // Handle or log the exception
        }

        // If content type is not detected, set it to application/octet-stream
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
