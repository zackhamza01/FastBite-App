# FastBite Food Ordering App

## Purpose

**What will my application do?**
- This application allows users to order *food* and/or *drinks* from their favorite fast food restaurants

**Who will use it?**
- For users who don't want to buy groceries or go drive to a fast-food restaurant in order to get food
- People that are *unable* to travel to get food, so getting food shipped to their house would be a big help 

**Why is this project of use to you?**
- This is a good opportunity for me show *future employers* what my coding capabilities are
- This project can potentially be a stepping stone for a career in application development

## User Stories
- As a user, I want to be able to add food to my order
- As a user, I want to be able to add drink(s) to my order
- As a user, I want to be able to pick a fast-food restaurant to order items from (by items, I mean food and drinks)
- As a user, I want to be able to see the pricing of the items before ordering
- As a user, I want to be able to be able to remove items from my order
- As a user, I want to be able to see the number of calories in each item
- As a user, I want to be able to finalize the order and ship that order to my address
- As a user, I want to be able to save my order to JSON file when I am quitting
- As a user, I want to be able to load my order of another session from JSON file

## Phase 4: Task 2
- A type hierarchy is implemented in this project that involves the Item, Food, and Drink classes
- In this hierarchy, the Item class is the abstract class that has an abstract getDescription() method
- The Food and Drink classes extend Item and override the getDescription() method with their respective override implementations
## Phase 4: Task 3
- Based on the UML Diagram, I would refactor the FastBiteApp to split it up into different components (classes).
- That is because the FastBiteApp has too many responsibilities in one single class, and its cohesion can be increased
by adding classes to take some of the many responsibilities 
- An example could be splitting the UI up into an OrderUI and FastFoodGUI to take care of those classes
- Another class that is needed for the UI is possibly an Address class, as the UI prompts the user to put their
- address after checking out
-This can be improved by including an Address class that would take care of this functionality
- In the GUI classes, there are also a lot of methods that are almost the same, which increases the coupling of this project
- This is bad as changing one of the implementations of these identical methods means the other methods also need to be changed
- A solution to this is implementing a design pattern to decrease the coupling in the GUI classes


