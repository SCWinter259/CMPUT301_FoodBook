# FoodBook

## About

This is a personal project, which borrows the idea from Assignment 1 of CMPUT 301, University of Alberta, Fall 2022. This is an Android app, allowing the user to keep track of their food storage.
This app is made using Java and Firebase.

## Project Specifications

Consider the situation someone facing rising costs and possibly food shortages in the longer term. As foods go on sile, they want to stock up, and track the contents of their food storage. Make a simple,
intuitive, Android mobile app to help mange this inventory and provide useful information while grocery shopping. Lat us call this app: FoodBook.

Specifically, each food entry has the following fields:
- description (textual, up to 30 characters)
- best before date (presented in yyy-mm-dd format)
- location (choice of pantry, freezer, or fridge)
- count (positive integer)
- unit cost (in dollars rounded up, positive integer)

The app must allow the user to:
- show a list of foods
- add a new food (which always appends to the bottom and of the list)
- view and edit the details of an existing food
- delete a food
- see the total cost, below the list

The list need not show all the information for an item if screen space is limited. Minimally, each item in the list should show its name, count, and unit cost.

The app must assist the user in proper data entry. For example, use appropriate user interface controls to enforce particular data types and avoid illegal values.