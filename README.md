# My Personal Project

## The Performance Archive

This application stores data about performances. A performance has three pieces of information:
- A **title**,
- the **year** it was performed, and
- the **event** it was performed at.

Performances are stored in collections, which also have titles.

The application has many uses. It was initially conceived to help performers such as choirs or pianists keep track of
what they had performed and where, so that they could avoid having repeat performances in quick succession (*i.e.* only
two years apart). It could also serve as a sort of historical archive, so that certain memorable performances could be
located, making it easy to know what year or event a recording could be sorted under. However, it could function
just as well for fans, who perhaps want to make sure they never forget the details of their favourite performances
they've attended.

This project is of interest to me as it involves my love of music, and seems somewhat useful to me as a small-time
performer. As a pianist and choir member, I have participated in many local events, and I know something that always
needs planning is what songs to sing or play. It is always desirable to have a unique performance, and give the
audience something new. However, with multiple songs throughout the year for different events, and year-long intervals
between some events, it can be hard to remember what was performed recently. So, the idea for this archive system
was born.

## User Stories

- As a user, I want to be able to add multiple performances to a collection
- As a user, I want to be able to create multiple collections with specific titles
- As a user, I want to be able to search a collection for all performances with a given title, year, or event
- As a user, I want to be able to filter a collection by year range
- As a user, I want to be able to save my archive to file
- As a user, I want to be able to load my archive from file

## Phase 4: Task 2

Fri Nov 19 19:59:43 PST 2021

Created new Christmas collection.

Fri Nov 19 20:00:03 PST 2021

Added I'm Dreaming of a White Christmas to Christmas collection.

Fri Nov 19 20:00:21 PST 2021

Added Carol of the Bells to Christmas collection.

## Phase 4: Task 3

- I would try to remove the bi-directional association between the ArchiveApp and ControlPanel classes. The only reason
the ControlPanel needs to know about the ArchiveApp is so that it can tell it what to do. However, after seeing the
Singleton principle in action in the EventLog class, I believe that I could apply that to the ArchiveApp, and the
ControlPanel could get the instance of the app whenever it wanted to call a method, much like how my model classes
don't need to know about the EventLog class to call the logEvent method.
- I wonder if there is a better way to deal with a selected collection in the ArchiveApp class. It already has access to
all the collections through the Archive class, maybe the selected collection should be stored in the Archive class, so
that there isn't a triangle of relationships.
