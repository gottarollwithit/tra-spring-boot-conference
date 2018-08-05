package com.gottarollwithit.traconference.helper;

import com.gottarollwithit.traconference.model.Talk;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Service
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ScheduleHelper {

    private boolean found = false;
    private List<Talk> solution;

    private void getAllSubsets(List<Talk> talkList, int i, int sum, Stack<Talk> currentSol) {
        if (found) return;

        //stop clauses:
        if (sum == 0 && i == talkList.size()) {
            found = true;
            solution = new ArrayList<>(currentSol);
        }
        //if elements must be positive, you can trim search here if sum became negative
        if (i == talkList.size()) return;
        //"guess" the current element in the list:
        currentSol.add(talkList.get(i));
        getAllSubsets(talkList, i + 1, sum - talkList.get(i).getDuration(), currentSol);
        //"guess" the current element is not in the list:
        currentSol.pop();
        getAllSubsets(talkList, i + 1, sum, currentSol);
    }

    public List<Talk> getSolution(List<Talk> talkList, Integer sum) {
        getAllSubsets(talkList, 0, sum, new Stack<>());
        return solution;
    }
}