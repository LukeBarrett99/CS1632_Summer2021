package edu.pitt.cs.cs1632;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class StepDefinitions {
	private RentACat r;
	private String listResult;
	private boolean rentResult;
	private boolean returnResult;
	
	// TODO: Add more member variables and methods as necessary

	@Given("a rent-a-cat facility")
	public void aRentACatFacility() {
		r = RentACat.createInstance();
	}
	
	@Given("no cats")
	public void noCats() {
		// nothing to do really
	}
	
	@Given("a cat with ID {int} and name {string}")
	public void aCatWithIDAndName(Integer id, String name) {
		r.addCat(new Cat(id, name));
		System.out.println("Created cat " + id + ". " + name);
	}
	
	@When("I list the cats")
	public void iListTheCats() {
		listResult = r.listCats();
	}
	
	@When("I rent cat number {int}")
	public void iRentCatNumber(Integer id) {
		if(r.catExists(id) && r.catAvailable(id))
		{
			r.rentCat(id);
			rentResult = true;
			System.out.println("Rented cat " + id);
		}
		else
		{
			rentResult = false;
			System.out.println("Cat " + id + "does not exist!");
		}
	}
	
	@When("I return cat number {int}")
	public void iReturnCatNumber(Integer int1) {
		if(r.catExists(int1) && !r.catAvailable(int1))
		{
			r.returnCat(int1);
			returnResult = true;
			System.out.println("Returned cat " + int1);
		}
		else
		{
			returnResult = false;
			System.out.println("Cat " + int1 + "does not exist!");
		}
	}
	
	@Then("the listing is: {string}")
	public void theListingIs(String result) {
		assertEquals(result.replaceAll("\\\\n", "\n"), listResult);
	}
	
	@Then("the rent is successful")
	public void theRentIsSuccessful() {
		assertTrue(rentResult);
	}

	@Then("the rent is unsuccessful")
	public void theRentIsUnsuccessful() {
		assertFalse(rentResult);
	}
	
	@Then("the return is successful")
	public void theReturnIsSuccessful() {
		assertTrue(returnResult);
	}
	
	@Then("the return is unsuccessful")
	public void theReturnIsUnsuccessful() {
		assertFalse(returnResult);
	}
	
}
