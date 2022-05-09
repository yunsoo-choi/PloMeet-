package com.ssafy.PloMeet.api.controller;

import com.ssafy.PloMeet.api.request.MeetingReq;

import com.ssafy.PloMeet.api.response.MeetingRes;
import com.ssafy.PloMeet.api.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/meetings")
public class MeetingController {

    private final MeetingService meetingService;

    //모임 개설
    @PostMapping("/host")
    public ResponseEntity<Object> createMeeting(@RequestBody MeetingReq meetingReq) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            responseMap.put("meetingId", meetingService.createMeeting(meetingReq));
        } catch (IllegalArgumentException e) {
            responseMap.put("errorMsg", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }

    //모임 상세정보 조회
    @GetMapping("/{meetingId}")
    public ResponseEntity findMeetingById(@PathVariable("meetingId") Long meetingId) {
        try {
            MeetingRes meetingRes = meetingService.findMeetingById(meetingId);
            return ResponseEntity.status(HttpStatus.OK).body(meetingRes);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }


}
