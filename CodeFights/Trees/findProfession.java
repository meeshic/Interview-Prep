//CodeFights:Trees:findProfession

/*
Consider a special family of Engineers and Doctors. This family has the following rules:

Everybody has two children.
The first child of an Engineer is an Engineer and the second child is a Doctor.
The first child of a Doctor is a Doctor and the second child is an Engineer.
All generations of Doctors and Engineers start with an Engineer.

Given the level and position of a person in the ancestor tree above, find the profession of the person.
Note: in this tree first child is considered as left child, second - as right.

Example

For level = 3 and pos = 3, the output should be
findProfession(level, pos) = "Doctor".
*/


String findProfession(int level, int pos) {
    if(level == 1) return "Engineer";
    
    String parent = findProfession(level-1, (pos+1)/2);
    
    if(parent.equals("Engineer") && pos%2==0) return "Doctor";
    if(parent.equals("Doctor") && pos%2==0) return "Engineer";
    
    return parent;
}

/*
Algorithm:
    - Recursive approach where the given position and the root 'node' are used to determine 
        ancestors' profession.
    - Key point: Position of child determines position of parent
        parent position = (child position + 1)/2
    - Using the above and the fact that engineers give birth to an engineer (left node) then 
        a doctor (right node) and vice versa for doctors, we can determine ancestors' 
        professions until we compute the desired node's profession.

Complexity:
    - Time: O(level), the # of times the function is called. Other than function call to itself, 
        rest of code is O(1).
    - Space: O(level), the max size of call stack
*/
