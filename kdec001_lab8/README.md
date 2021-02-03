
# Lab 8

## Student information
* Full name: Kristian De Castro
* E-mail: kdec001@ucr.edu
* UCR NetID: kdec001
* Student ID: 862025678

## Answers

* (Q1) Insert the sample JSON file into a new collection named contacts.

`db.contacts.insertMany ( contacts.json contents )`


* (Q2) Retrieve all the users sorted by name.

`db.contacts.find().sort({Name:1})`


* (Q3) List only the id and names sorted in reverse alphabetical order by name (Z-to-A).

`db.contacts.find({},{Name:1}).sort({Name:-1})`


* (Q4) Is the comparison of the attribute name case-sensitive? Show how you try this with the previous query and include your answer.

`db.contacts.find({},{namE:1}).sort({Name:-1})`
Yes since the contacts variable name is "Name" and not any other name


* (Q5) Repeat Q3 above but do not show the \_id field.

`db.contacts.find({},{Name:1, _id:0}).sort({Name:-1})`


* (Q6) Insert the following document to the collection.

`db.contacts.insertOne({Name: {First: "David", Last: "Bark"}})`
Yes, mongo did accept this because the name field takes in any string after it as long as it is in the brackets

* (Q7) Where do you expect the new record to be located in the sort order? Verify the answer and explain.

I expect David Bark to be between Hayes Weaver and Craft Parks because "F" is between "C" and "H".
`db.contacts.find({},{Name:1, _id:0}).sort({Name:-1})`
However after running the command in Q3, it is in the beginning. I think this is because since First and David are not separated by commans, it combines the two and has a value greater than the other names? I'm not quite sure.


* (Q8) Where do you expect the new document to appear in the sort order. Verify your answer and explain after running the query.

I expect the new document to appear between Hayes and Craft still.
`db.contacts.find({},{Name:1, \_id:0}).sort({Name:-1})`
After running it, my expectation was correct because this is an array of names and it is sorted based on the first index.


* (Q9) Where do you expect the last inserted record, {Name: [“David”, “Bark”]} to appear this time? Does it appear in the same position relative to the other records? Explain why or why not.

I still expect it to be between Hayes and Craft.
`db.contacts.find({},{Name:1, \_id:0}).sort({Name:1})`
It did not appear where I expected. This happened because since it's an array of names, it took the Barks part of the array and sinces B is less than D, it placed it between A and C


* (Q10) Build an index on the Name field for the users collection. Is MongoDB able to build the index on that field with the different value types stored in the Name field?

`db.contacts.createIndex({Name:1})`
Yes it was able to build the index because there was no specification prior to creating it.
