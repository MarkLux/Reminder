package com.marklux.reminder.service;

import com.marklux.reminder.domain.Remind;
import com.marklux.reminder.mapper.RemindMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lumin on 18/6/23.
 */
@Service
public class RemindService {
    @Autowired
    private RemindMapper remindMapper;

    public Map<String, List<Remind>> getRemindsByUserId(int userId) {
        List<Remind> remindList = remindMapper.getRemindsByUserId(userId);
        if (remindList == null || remindList.size() == 0) {
            return new HashMap<>();
        }
        Map<String, List<Remind>> resultMap = new HashMap<>();

        List<Remind> pinnedReminds = new ArrayList<>();
        List<Remind> unpinnedReminds = new ArrayList<>();

        for (Remind r:remindList) {
            if (r.getIsPinned() == 0) {
                unpinnedReminds.add(r);
            }else if (r.getIsPinned() == 1) {
                pinnedReminds.add(r);
            }
        }

        resultMap.put("pinned", pinnedReminds);
        resultMap.put("unpinned", unpinnedReminds);

        return resultMap;
    }

    public void createRemind(Remind remind) {
        remind.setCreatedAt(System.currentTimeMillis());
        remindMapper.createRemind(remind);
    }

    public void deleteRemind(long remindId) {
        remindMapper.deleteRemind(remindId);
    }

    public void updateRemindPinStatus(long remindId, short pinStatus) {
        Remind remind = this.remindMapper.getRemind(remindId);
        if (remind != null) {
            remind.setIsPinned(pinStatus);
            this.remindMapper.updateRemind(remind);
        }
    }
}
