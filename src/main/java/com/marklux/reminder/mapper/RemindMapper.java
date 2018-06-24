package com.marklux.reminder.mapper;

import com.marklux.reminder.domain.Remind;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lumin on 18/6/23.
 */
public interface RemindMapper {
    Remind getRemind(long id);
    List<Remind> getRemindsByUserId(int userId);
    void createRemind(Remind remind);
    void deleteRemind(long id);
    void updateRemind(Remind remind);
}
