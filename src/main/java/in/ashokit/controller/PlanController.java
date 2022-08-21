package in.ashokit.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.ashokit.entity.Plan;
import in.ashokit.serviceI.PlanServiceI;

@RestController
@RequestMapping("/plan")
public class PlanController {

	@Autowired
	PlanServiceI planServiceI;

	@GetMapping(value = "/categories")
	public ResponseEntity<Map<Integer, String>> planCategories() {
		Map<Integer, String> categories = planServiceI.getPlanCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {
		boolean planSaved = planServiceI.savePlan(plan);

		String responseMsg = " ";

		if (planSaved) {
			responseMsg = "Plan saved Sucessfully !!";
		} else {
			responseMsg = "Plan not saved";
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
	}

	@GetMapping(value = "/allplans")
	public ResponseEntity<List<Plan>> allPlans() {
		List<Plan> allPlans = planServiceI.getAllPlans();
		return new ResponseEntity<List<Plan>>(allPlans, HttpStatus.OK);
	}

	@GetMapping(value = "/{planId}")
	public ResponseEntity<Plan> planById(@PathVariable Integer planId) {
		Plan planById = planServiceI.getPlanById(planId);
		return new ResponseEntity<>(planById, HttpStatus.OK);
	}

	@DeleteMapping(value = "delete/{planId}")
	public ResponseEntity<String> deletePlanById(@PathVariable Integer planId) {

		boolean deletePlanById = planServiceI.deletePlanById(planId);
		String msg = "";

		if (deletePlanById) {
			msg = "plan deleted successfully !!";
		} else {
			msg = "plan not deleted";
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PutMapping(value = "/updatePlan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {
		boolean planUpdated = planServiceI.updatePlan(plan);

		String message = "";
		if (planUpdated) {
			message = "Plan updated Succesffuly !!";
		} else {
			message = "Plan not updated";
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@PutMapping(value = "/statusChange/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId, @PathVariable String status) {
		boolean planStatusChange = planServiceI.planStatusChange(planId,status);
		
		String mesg = "";
		if (planStatusChange) {
			mesg ="plan status changed successfuly";
		} else {
			mesg ="plan status not changed";
		}
		return new ResponseEntity<>(mesg, HttpStatus.OK);
	}

}
