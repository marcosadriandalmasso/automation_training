package com.globant.training.marcos_dalmasso.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage {

	@FindBy(id="post-meta")
	private WebElement postDate;
	
	public String getPostDate(){
		String date [] = postDate.getText().split("@");
		return date[0].trim();
	}
}
