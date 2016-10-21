package com.ivaniv.validation;

import com.ivaniv.domain.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by iivaniv on 20.10.2016.
 */
public class TaskValidator {

    private static final Logger log = LoggerFactory.getLogger(TaskValidator.class);

    public static Task validate(String taskPriority, String taskName, String taskDate) throws IllegalArgumentException{

        Integer priority;
        Timestamp timestamp ;

        try {
            LocalTime time = LocalTime.parse(taskDate);

            Calendar datetime = new GregorianCalendar();
            datetime.set(Calendar.HOUR_OF_DAY, (time.getHour()));
            datetime.set(Calendar.MINUTE, (time.getMinute()));

            Date date = datetime.getTime();
            System.out.println(date);

            timestamp = new Timestamp(date.getTime());


            priority = Integer.valueOf(taskPriority);
        }catch (Exception e){
            return null;
        }
        return new Task(priority,timestamp, taskName );

    }

}
