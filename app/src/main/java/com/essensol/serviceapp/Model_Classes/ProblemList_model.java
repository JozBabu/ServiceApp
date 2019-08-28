package com.essensol.serviceapp.Model_Classes;

public class ProblemList_model {

    String ProblemId,Problem;

    public ProblemList_model(String problemId, String problem) {
        ProblemId = problemId;
        Problem = problem;
    }

    public String getProblemId() {
        return ProblemId;
    }

    public void setProblemId(String problemId) {
        ProblemId = problemId;
    }

    public String getProblem() {
        return Problem;
    }

    public void setProblem(String problem) {
        Problem = problem;
    }

    @Override
    public String toString() {
        return Problem ;
    }
}

