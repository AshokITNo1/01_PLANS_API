package in.ashokit.serviceI;

import java.util.List;
import java.util.Map;
import in.ashokit.entity.Plan;

public interface PlanServiceI {

	// KEY--> categoryId -->To store , VALUE-->categoryName-->To display
	public Map<Integer, String> getPlanCategories();

	// GET ALL DATA FOR PLAN
	public List<Plan> getAllPlans();

	// GET SINGLE DATA
	public Plan getPlanById(Integer planId);

	// DELETE SINGLE DATA
	public boolean deletePlanById(Integer planid);

	// POST/SAVE ALL DATA
	public boolean savePlan(Plan plan);

	// UPDATE SINGLE DATA
	public boolean updatePlan(Plan plan);

	//	To update and save in one method
	//	public boolean upsertPlan(Plan plan);

	// PLAN STATUS CHANGE FOR planId --> Status Activate & Deactivate with 2
	// parameters
	public boolean planStatusChange(Integer planId, String status);

}
