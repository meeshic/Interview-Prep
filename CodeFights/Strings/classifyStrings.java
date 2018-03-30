//CodeFights:Strings:classifyStrings

/*
Problem: 
You categorize strings into three types: good, bad, or mixed. If a string has 3 consecutive vowels
or 5 consecutive consonants, or both, then it is categorized as bad. Otherwise it is categorized 
as good. Vowels in the English alphabet are ["a", "e", "i", "o", "u"] and all other letters are 
consonants.

The string can also contain the character ?, which can be replaced by either a vowel or a consonant. 
This means that the string "?aa" can be bad if ? is a vowel or good if it is a consonant. This kind 
of string is categorized as mixed.

Implement a function that takes a string s and returns its category: good, bad, or mixed.
*/

/*
Thoughts:
    - For the c == '?' case, we want to make two copies of the string:
        1. where c = vowel
        2. where c = consonant
    - With a recursive function, it's easier to make 'copies' of the string
*/

/*
Complexity:
    Time: O(N*2^(# of ?'s)), essentially 2^(# of ?'s) copies of the string of being examined, N=length of string
        > worse case being the string all ?'s
    Space: O(N), stack space is at most N
*/



String classifyStrings(String s) {
    if(s.length() == 1) return "good";
    
    return findStringType(s, 0, s.charAt(0), isVowel(s.charAt(0)), 0);
}

boolean isVowel(char c){
    return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
}

String findStringType(String s, int index, char c, boolean vowel, int count){    
    if(c == '?'){
        String ifVowel = findStringType(s, index, 'a', vowel, count);
        String ifCons = findStringType(s, index, 'b', vowel, count);
        
        if(ifVowel.equals(ifCons))
            return ifVowel;
        else
            return "mixed";
    }
    else if(isVowel(c)){
        if(vowel && (++count == 3))return "bad";
        if(!vowel) count=1;
    }
    else{ //consonant
        if(!vowel && ++count == 5) return "bad";
        if(vowel) count=1;
    }

    if(++index == s.length()) return "good";
    
    return findStringType(s, index, s.charAt(index), isVowel(c), count);
}


/*
Regex solution:

String classifyStrings(String s) {
    if(s.matches(".*([aeiou]{3}|[bcdfghjklmnpqrstvwxyz]{5}).*"))
        return "bad";
    if(s.matches(".*([aeiou?]{3}|[bcdfghjklmnpqrstvwxyz?]{5}).*"))
        return "mixed";
    return "good";
}
*/
