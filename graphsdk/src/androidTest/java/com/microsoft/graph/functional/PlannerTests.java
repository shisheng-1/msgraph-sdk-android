package com.microsoft.graph.functional;

import android.test.AndroidTestCase;

//import com.microsoft.graph.extensions.IDirectoryDeletedItemsCollectionPage;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;;
import com.google.gson.JsonPrimitive;
import com.microsoft.graph.extensions.Group;
import com.microsoft.graph.extensions.GroupRequestBuilder;
import com.microsoft.graph.extensions.IDirectoryObjectCollectionPage;
import com.microsoft.graph.extensions.IDriveItemCollectionPage;
import com.microsoft.graph.extensions.IDriveItemCollectionRequestBuilder;
import com.microsoft.graph.extensions.IGraphServiceClient;
import com.microsoft.graph.extensions.IGroupCollectionPage;
import com.microsoft.graph.extensions.IPlannerBucketCollectionPage;
import com.microsoft.graph.extensions.IPlannerBucketRequest;
import com.microsoft.graph.extensions.IPlannerPlanCollectionPage;
import com.microsoft.graph.extensions.IPlannerPlanRequest;
import com.microsoft.graph.extensions.IPlannerPlanRequestBuilder;
import com.microsoft.graph.extensions.IPlannerTaskCollectionPage;
import com.microsoft.graph.extensions.IPlannerTaskRequest;
import com.microsoft.graph.extensions.ITaskCollectionPage;
import com.microsoft.graph.extensions.PlannerAssignedToTaskBoardTaskFormat;
import com.microsoft.graph.extensions.PlannerAssignment;
import com.microsoft.graph.extensions.PlannerAssignments;
import com.microsoft.graph.extensions.PlannerBucket;
import com.microsoft.graph.extensions.PlannerBucketCollectionPage;
import com.microsoft.graph.extensions.PlannerBucketRequest;
import com.microsoft.graph.extensions.PlannerBucketTaskBoardTaskFormat;
import com.microsoft.graph.extensions.PlannerChecklistItems;
import com.microsoft.graph.extensions.PlannerExternalReferences;
import com.microsoft.graph.extensions.PlannerPlan;
import com.microsoft.graph.extensions.PlannerProgressTaskBoardTaskFormat;
import com.microsoft.graph.extensions.PlannerTask;
import com.microsoft.graph.extensions.PlannerTaskDetails;
import com.microsoft.graph.extensions.Task;
import com.microsoft.graph.options.HeaderOption;
import com.microsoft.graph.serializer.AdditionalDataManager;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public class PlannerTests extends AndroidTestCase {
    private TestBase testBase;
    // For now, you must specify a specific plan ID since the test cannot
    // clean up after itself
    private String planId = "bVQECPYdRkqg3c9Mp4mJbmQADG1N";
    private PlannerBucket planBucket;
    private PlannerTask planTask;

    public void setUp() {
        testBase = new TestBase();
        testBase.graphClient.setServiceRoot("https://graph.microsoft.com/stagingbeta");

        PlannerBucket newBucket = new PlannerBucket();
        newBucket.name = "Test Bucket";
        newBucket.planId = planId;

        planBucket = testBase.graphClient.getPlanner().getBuckets().buildRequest().post(newBucket);

        PlannerTask newTask = new PlannerTask();
        newTask.title = "Test Task";
        newTask.planId = planId;
        newTask.bucketId = planBucket.id;

        planTask = testBase.graphClient.getPlanner().getTasks().buildRequest().post(newTask);
    }

    public void testPostTask() {
        PlannerTask newTask = new PlannerTask();
        newTask.title = "Test1";
        newTask.planId = planId;
        newTask.bucketId = planBucket.id;

        PlannerTask task = testBase.graphClient.getPlanner().getTasks().buildRequest().post(newTask);
        assertNotNull(task);
    }

    public void testBucketTaskBoardFormat() {
        PlannerBucketTaskBoardTaskFormat format = testBase.graphClient.getPlanner().getTasks(planTask.id).getBucketTaskBoardFormat().buildRequest().get();
        assertNotNull(format);
    }

    public void testAssignedToTaskBoardFormat() {
        PlannerAssignedToTaskBoardTaskFormat format = testBase.graphClient.getPlanner().getTasks(planTask.id).getAssignedToTaskBoardFormat().buildRequest().get();
        assertNotNull(format);
    }

    public void testProgressTaskBoardFormat() {
        PlannerProgressTaskBoardTaskFormat format = testBase.graphClient.getPlanner().getTasks(planTask.id).getProgressTaskBoardFormat().buildRequest().get();
        assertNotNull(format);
    }

    public void testGetTaskDetails() {
        PlannerTaskDetails details = testBase.graphClient.getPlanner().getTasks(planTask.id).getDetails().buildRequest().get();
        assertNotNull(details);
    }

    public void testUpdateTask() {
//        PlannerTask task = testBase.graphClient.getPlanner().getTasks(planTask.id).buildRequest().get();
//
//        PlannerAssignments assignments = new PlannerAssignments();
//        PlannerAssignment assignment = new PlannerAssignment();
//        assignment.orderHint = "!";
//        assignment.oDataType = "#microsoft.graph.plannerAssignment";
//        assignments.getAdditionalDataManager();
//
//        task.assignments = assignments;
//
//        PlannerTask returnTask = testBase.graphClient.getPlanner().getTasks(planTask.id).buildRequest().patch(task);
//
//        assertNotNull(returnTask);
    }

    public void testUpdateTaskDetailsChecklist() {
//        PlannerTaskDetails details = new PlannerTaskDetails();
//
//        details.checklist = new PlannerChecklistItems();
//
//        PlannerTaskDetails returnTaskDetails = testBase.graphClient.getPlanner().getTasks(planTask.id).getDetails().buildRequest().patch(details);
//
//        assertNotNull(returnTaskDetails);
    }

    public void testUpdateTaskDetails() {
//        PlannerTaskDetails details = new PlannerTaskDetails();
//
//        details.references = new PlannerExternalReferences();
//
//        PlannerTaskDetails returnTaskDetails = testBase.graphClient.getPlanner().getTasks(planTask.id).getDetails().buildRequest().patch(details);
//
//        assertNotNull(returnTaskDetails);
    }

    public void testUpdateTaskCompletion() {
        PlannerTask task = new PlannerTask();
        task.percentComplete = 50;

        IPlannerTaskRequest req = testBase.graphClient.getPlanner().getTasks(planTask.id).buildRequest();
        req.addHeader("If-Match", getEtag(planTask.getRawObject()));
        req.patch(task);

        PlannerTask updatedTask = testBase.graphClient.getPlanner().getTasks(planTask.id).buildRequest().get();

        assertEquals(task.percentComplete, updatedTask.percentComplete);
    }

    public void testUpdateTaskStartDate() {
        PlannerTask task = new PlannerTask();
        task.startDateTime = Calendar.getInstance();

        IPlannerTaskRequest req = testBase.graphClient.getPlanner().getTasks(planTask.id).buildRequest();
        req.addHeader("If-Match", getEtag(planTask.getRawObject()));
        req.patch(task);

        PlannerTask updatedTask = testBase.graphClient.getPlanner().getTasks(planTask.id).buildRequest().get();
        updatedTask = testBase.graphClient.getPlanner().getTasks(planTask.id).buildRequest().get();
        assertEquals(task.startDateTime, updatedTask.startDateTime);
    }

    public void testUpdateTaskDueDate() {
        PlannerTask task = new PlannerTask();
        task.dueDateTime = Calendar.getInstance();

        IPlannerTaskRequest req = testBase.graphClient.getPlanner().getTasks(planTask.id).buildRequest();
        req.addHeader("If-Match", getEtag(planTask.getRawObject()));
        req.patch(task);

        PlannerTask updatedTask = testBase.graphClient.getPlanner().getTasks(planTask.id).buildRequest().get();
        updatedTask = testBase.graphClient.getPlanner().getTasks(planTask.id).buildRequest().get();
        assertEquals(task.dueDateTime, updatedTask.dueDateTime);
    }

    public void testUpdateTaskCategories() {
    }

    public void testUpdatePlanDetails() {

    }

    public void testDeleteTask() {
        PlannerTask newTask = new PlannerTask();
        newTask.title = "Delete Me";
        newTask.planId = planId;
        newTask.bucketId = planBucket.id;

        PlannerTask task = testBase.graphClient.getPlanner().getTasks().buildRequest().post(newTask);

        IPlannerTaskRequest req = testBase.graphClient.getPlanner().getTasks(task.id).buildRequest();
        req.addHeader("If-Match", getEtag(task.getRawObject()));
        req.delete();
    }

    public void testCreateBucket() {
        PlannerBucket newBucket = new PlannerBucket();
        newBucket.name = "Create Bucket Test";
        newBucket.planId = planId;

        PlannerBucket createdBucket = testBase.graphClient.getPlanner().getBuckets().buildRequest().post(newBucket);
        assertEquals(newBucket.name, createdBucket.name);
    }

    public void testUpdateBucket() {
        //planBucket.name = "RenamedBucket";
        PlannerBucket patchBucket = new PlannerBucket();
        patchBucket.name = "RenamedBucket";
        patchBucket.oDataType = "#microsoft.graph.plannerBucket";

        IPlannerBucketRequest req = testBase.graphClient.getPlanner().getBuckets(planBucket.id).buildRequest();
        req.addHeader("If-Match", getEtag(planBucket.getRawObject()));

        req.patch(patchBucket);
        PlannerBucket updatedBucket = testBase.graphClient.getPlanner().getBuckets(planBucket.id).buildRequest().get();

        assertEquals(patchBucket.name, updatedBucket.name);

        patchBucket.name = "Test Bucket";
        IPlannerBucketRequest req2 = testBase.graphClient.getPlanner().getBuckets(planBucket.id).buildRequest();
        req2.addHeader("If-Match", getEtag(updatedBucket.getRawObject()));
        req2.patch(patchBucket);
    }

    public void testDeleteBucket() {
        PlannerBucket newBucket = new PlannerBucket();
        newBucket.name = "Delete Me";
        newBucket.planId = planId;

        PlannerBucket createdBucket = testBase.graphClient.getPlanner().getBuckets().buildRequest().post(newBucket);

        IPlannerBucketRequest req = testBase.graphClient.getPlanner().getBuckets(createdBucket.id).buildRequest();
        req.addHeader("If-Match", getEtag(createdBucket.getRawObject()));
        req.delete();
    }

    public void testUpdateAssignedToTaskBoardFormat() {

    }

    public void tearDown() {
        // This may have updated since we last saw it
        PlannerTask task = testBase.graphClient.getPlanner().getTasks(planTask.id).buildRequest().get();
        IPlannerTaskRequest taskReq = testBase.graphClient.getPlanner().getTasks(planTask.id).buildRequest();
        taskReq.addHeader("If-Match", getEtag(task.getRawObject()));
        taskReq.delete();

        PlannerBucket bucket = testBase.graphClient.getPlanner().getBuckets(planBucket.id).buildRequest().get();
        IPlannerBucketRequest bucketReq = testBase.graphClient.getPlanner().getBuckets(planBucket.id).buildRequest();
        bucketReq.addHeader("If-Match", getEtag(bucket.getRawObject()));
        bucketReq.delete();

//Fails with 403 Forbidden
//        PlannerPlan plan = testBase.graphClient.getPlanner().getPlans(planId).buildRequest().get();
//        IPlannerPlanRequest planReq = testBase.graphClient.getPlanner().getPlans(planId).buildRequest();
//        planReq.addHeader("If-Match", getEtag(plan.getRawObject()));
//        planReq.delete();
    }

    public String getEtag(JsonObject obj) {
        String etag = obj.get("@odata.etag").toString();
        etag = etag.substring(1, etag.length()-1);
        etag = etag.replace("\\", "");
        return etag;
    }
}