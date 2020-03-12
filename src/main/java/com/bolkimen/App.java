package com.bolkimen;

import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.gson.GsonDecoder;

import java.text.BreakIterator;
import java.util.*;

import static java.util.Collections.emptySet;
import static org.springframework.util.CollectionUtils.isEmpty;

interface GitHub {
    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);

    @RequestLine("POST /repos/{owner}/{repo}/issues")
    void createIssue(Issue issue, @Param("owner") String owner, @Param("repo") String repo);

    static GitHub connect() {
        return Feign.builder()
                .decoder(new GsonDecoder())
                .target(GitHub.class, "https://api.github.com");
    }

}

class Contributor {
    String login;
    int contributions;
}

class Issue {
    String title;
    String body;
    List<String> assignees;
    int milestone;
    List<String> labels;
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }

/**
 * Hello world!
 *
 */
public class App 
{
    public static void printEachForward(BreakIterator boundary, String source) {
        int start = boundary.first();
        for (int end = boundary.next();
             end != BreakIterator.DONE;
             start = end, end = boundary.next()) {
            System.out.println(source.substring(start,end));
        }
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public static void checkAnyRole(Set<String> configuredRoles, Set<String> userRoles) {
        if (isEmpty(configuredRoles)) {
            //log.debug("Resource has no roles to check against.");
            return;
        }

        if (!hasAnyRole(configuredRoles, Optional.ofNullable(userRoles).orElse(emptySet()))) {
            //log.debug("user has no role allowing access");
            String s = "";
            //throw new AuthorizationException("User is not authorized to access this resource.");
        }
    }

    private static boolean hasAnyRole(Set<String> configuredRoles, Set<String> userRoles) {
        return userRoles.stream().anyMatch(configuredRoles::contains);
    }

    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int currentLength = 0;

        Map<Character, Integer> charsInSubstring = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char currentChar = chars[i];
            if (charsInSubstring.containsKey(currentChar) && currentLength >= i - charsInSubstring.get(currentChar)) {
                currentLength = i - charsInSubstring.get(currentChar);
            } else {
                currentLength++;
            }

            charsInSubstring.put(currentChar, i);
            if (currentLength > maxLength) {
                maxLength = currentLength;
            }
        }

        return maxLength;
    }

    public void test() throws Exception {
        wait();
    }

    public static void main( String[] args ) throws Exception
    {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(lengthOfLongestSubstring("tmmzuxt")); // 5
        System.out.println(lengthOfLongestSubstring("abba")); // 2

        App app = new App();
        app.test();

    }
}
