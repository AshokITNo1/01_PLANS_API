package in.ashokit.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Plan;
import in.ashokit.entity.PlanCategory;
import in.ashokit.repository.PlanCategoryRepo;
import in.ashokit.repository.PlanRepo;
import in.ashokit.serviceI.PlanServiceI;

@Service
public class PlanServiceImpl implements PlanServiceI {

	@Autowired
	PlanRepo planRepo;

	@Autowired
	PlanCategoryRepo planCategoryRepo;

	@Override
	public Map<Integer, String> getPlanCategories() {
		List<PlanCategory> categories = planCategoryRepo.findAll();

		Map<Integer, String> categoryMap = new HashMap<>();

		categories.forEach(category -> {
			categoryMap.put(category.getCategoryId(), category.getCategoryName());
		});

		return categoryMap;
	}

	@Override
	public List<Plan> getAllPlans() {
		return planRepo.findAll();
	}

	@Override
	public Plan getPlanById(Integer planId) {
		Optional<Plan> findById = planRepo.findById(planId);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean deletePlanById(Integer planid) {

		boolean status = false;
		try {
			planRepo.deleteById(planid);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public boolean savePlan(Plan plan) {

		Plan saved = planRepo.save(plan);

		/*
		 * if (saved.getPlanId() != null) { return true;
		 * 
		 * } else { return false; }
		 */

		// 2year experience

		// return saved.getPlanId()!=null ? true : false;

		// 3year experience

		return saved.getPlanId() != null;

	}

	@Override
	public boolean updatePlan(Plan plan) {
		planRepo.save(plan);
		return plan.getPlanId() != null;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		Optional<Plan> findById = planRepo.findById(planId);
		if (findById.isPresent()) {
			Plan plan = findById.get();
			plan.setActiveSw(status);
			planRepo.save(plan);
			return true;
		}

		return false;
	}

}
