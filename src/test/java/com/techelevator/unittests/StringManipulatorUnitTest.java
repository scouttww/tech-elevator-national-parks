package com.techelevator.unittests;

import org.junit.Test;

import com.techelevator.npgeek.tools.StringManipulator;

import junit.framework.*;

@SuppressWarnings("deprecation")
public class StringManipulatorUnitTest {

	@Test
	public void convert_to_camelcase_null_string_returns_null_string() {
		Assert.assertEquals(StringManipulator.convertToCamelCase(""), "");
	}

	@Test
	public void convert_to_camelcase_space_string_returns_space_string() {
		Assert.assertEquals(StringManipulator.convertToCamelCase(" "), " ");
	}

	@Test
	public void convert_to_camelcase_1_word_string_returns_unchanged_string() {
		Assert.assertEquals(StringManipulator.convertToCamelCase("hello"), "hello");
	}

	@Test
	public void convert_to_camelcase_camelcase_string_returns_unchanged_string() {
		Assert.assertEquals(StringManipulator.convertToCamelCase("helloWorld"), "helloWorld");
	}

	@Test
	public void convert_to_camelcase_2_word_string_second_word_uppercase_returns_combined_string() {
		Assert.assertEquals(StringManipulator.convertToCamelCase("hello World"), "helloWorld");
	}

	@Test
	public void convert_to_camelcase_2_word_string_second_word_lowercase_returns_combined_camelcase_string() {
		Assert.assertEquals(StringManipulator.convertToCamelCase("hello world"), "helloWorld");
	}

	@Test
	public void convert_to_camelcase_3_word_string_second_and_third_words_already_camelCase_returns_combined_camelcase_string() {
		Assert.assertEquals(StringManipulator.convertToCamelCase("hello worldWorld"), "helloWorldWorld");
	}

	@Test
	public void convert_to_camelcase_3_word_string_second_and_third_words_lowercase_returns_combined_camelcase_string() {
		Assert.assertEquals(StringManipulator.convertToCamelCase("hello world world"), "helloWorldWorld");
	}

}
