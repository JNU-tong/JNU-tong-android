package kr.ac.jejunu.jnu_tong.task;

import java.util.List;

/**
 * Created by seung-yeol on 2018. 4. 9..
 */

public interface TaskResult<E> {
    void setTaskResult(List<E> result);
}
