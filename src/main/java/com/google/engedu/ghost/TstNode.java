package com.google.engedu.ghost;

import java.util.ArrayList;

public class TstNode {

   public int firstTime=0;
    public int position=0;
    String original;
    private boolean bool;
    TstNode left,mid,right;
    String alpha;

    TstNode()
    {
        bool=false;
        left=mid=right=null;

    }

    public TstNode(String a)
    {
        alpha=a;
        bool=false;
        left=mid=right=null;

    }

    public void add(String word)
    {

        if(firstTime==0) {
            firstTime=1;
            original = word;
           // int position=-1;

        }
        String previous;
       // String previous= word.charAt(position)+"";
        TstNode newNode=new TstNode();
        TstNode prev=new TstNode();
        TstNode temp=this;
        String t="";
        int signal=0;
        if(word.length()!=0)
        {

            prev=temp;
            previous=t;
            t=word.substring(0, 1);

            if(t.compareTo(temp.alpha)<0)
            {
                temp=temp.left;
                signal=1;
            }
            else if(t.compareTo(temp.alpha)>0) {
                temp = temp.right;
                signal=1;
            }
            else
            {
                word=word.substring(1);
                temp=temp.mid;
            }
            if(temp==null)
            {
                if(signal==0)
                {previous=t;}

                newNode= new TstNode(t);
                temp=newNode;
                if(prev.mid==null && prev.alpha==previous)
                    prev.mid=temp;
                else {
                    if (t.compareTo(prev.alpha) < 0) {
                        prev.left = newNode;
                    } else if (t.compareTo(prev.alpha) > 0)
                        prev.right = newNode;
                    else prev.mid = newNode;
                   }
                 if (word.length() > 0) {

                     if(prev.alpha!=newNode.alpha)
                          word = word.substring(1);

                    temp.add(word);
                }

               /* else
                 {
                     temp.bool=true;
                 }*/
            }
            else temp.add(word);
        }

        else
            temp.bool=true;

        //position++;
    }

    public boolean isWord(String word)
    {

        String t;
        TstNode temp=this;
        if(word.length()!=0)
        {
            t=word.substring(0, 1);

            if(t.compareTo(temp.alpha)<0)
            {
                temp=temp.left;
                if(temp==null)
                    return false;
                return temp.isWord(word);
            }
            else if(t.compareTo(temp.alpha)>0) {
                temp=temp.right;
                if(temp==null)
                    return false;
                return temp.isWord(word);
            }
            else
            {
                if(word.length()==1)
                {
                    return temp.bool;
                }
                else
                {	word=word.substring(1);
                    temp=temp.mid;
                    if(temp==null)
                        return false;
                    return temp.isWord(word);
                }

            }

        }

            return false;
    }

    public String getAnyWordStartingWith(String prefix, int length)
    {
        int count=0;
        int len=0;
        TstNode endOfPrefixChild;
        String append="";
        TstNode temp=this;
        String str;
        while(!temp.alpha.equalsIgnoreCase(prefix.substring(0,1)))
        {
            temp=temp.right;
        }

        while(count<prefix.length())
        {
             str=prefix.charAt(count)+"";
            int diff=str.compareToIgnoreCase(temp.alpha);
            if(diff==0)
            {
                count++;
                append=append+str;
                temp=temp.mid;
            }
            else if(diff>0)
            {
                temp=temp.right;
            }
            else
            {
                temp=temp.left;
            }
        }

        endOfPrefixChild=temp;
        len=prefix.length();

        while(temp!=null)
        {
            while (temp!=null)
            {
                append = append + temp.alpha;
                len++;
                if(temp.bool==true)
                    break;
                temp = temp.mid;
            }
            if (len == length)
                return append;
            else
            {
                append=prefix;
                len=prefix.length();
                temp = endOfPrefixChild.right;
            }

        }
        return "No prefix";
    }

public String suggestion2(String wrong)
{
    String original=wrong;
    int i, outer;
    String alphabet="abcdefghijklmnopqrstuvwxyz";
    for(outer=0;outer<original.length();outer++)
    {
        for (i = 0; i < 26; i++) {
            String prev = wrong.substring(0, outer);
            String next = wrong.substring(outer + 1);
            String changed = prev + alphabet.charAt(i) + next;
            if (isWord(changed))
                return changed;
        }
    }
    return "No suggestion";
}


    public String suggestion(String wrong)
    {

        String original=wrong;
        int i, outer;
        String alphabet="abcdefghijklmnopqrstuvwxyz";
        for(outer=0;outer<original.length();outer++)
        {
            for (i = 0; i < 26; i++)
            {
                String prev=wrong.substring(0,outer);
                String next=wrong.substring(outer+1);
                String changed=prev+alphabet.charAt(i)+next;
                if (isWord(changed))
                    return changed;
            }
            //fix
            wrong = original;
            String prefix=wrong.substring(0,outer+1);
            String word= getAnyWordStartingWith(prefix, wrong.length());
            if(word.equalsIgnoreCase("No prefix"))
                return "No suggestion";
            else
                return word;
        }

        return "No suggestion";

    }
}


