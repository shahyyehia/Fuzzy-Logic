# Fuzzy-Logic
## The problem is to estimate the level of risk involved in a software engineering project.
### Sets
* The sets defined for project_funding are very low (0,0,10,30), low (10,30,40,60), medium (40,60,70,90), high (70,90,100,100).
* The sets defined for team_experience_level are beginner (0,15,30), intermediate (15,30,45), expert (30,60,60).
* The set defined for the risk is low (0,25,50), normal (25,50,75), high (50,100,100).
### The Rules
* 1. If project_funding is high or team_experience_level is expert then risk is low.
* 2. If project_funding is medium and team_experience_level is intermediate or team_experience_level is beginner then risk is normal.
* 3. If project_funding is very low then risk is high.
* 4. If project_funding is low and team_experience_level is beginner then risk is high.
### Input:
* 1- First line represents number of input variables = 2
* 2- Second line gives a Variable Name and its crisp input to fuzzify it later (e.g. project funding 50, experience level 40).
### Output:
* 1- Fuzzifying the inputs
* 2- Inference of rules
* 3- Defuzzification output
