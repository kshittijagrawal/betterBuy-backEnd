package com.example.UserClient.dto;

import lombok.Data;

import java.util.List;

@Data
public class HistoryList {
    public List<History> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<History> historyList) {
        this.historyList = historyList;
    }

    List<History> historyList;
}
