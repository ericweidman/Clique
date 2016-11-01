                                          -- Design Document for Clique --
                    
                                            -- (Current) Technologies --
                    
                              Java - Spring/Hibernate - Postgres - HTML - Javascript - CSS

                              
**Features:**  
Unique User Accounts  
Friends List  
Direct Chat  
User Created Cliques (chatrooms)  
Voting system to add additional users to Cliques  


                                                   -- Road Map --
                    
                          Use Google Identity API and/or Facebook Login API for authentication
                        Users who decide to terminate thier clique can be kicked by vote instead


                    
**User Account**:  
Size: Medium

The user will need to provide a valid e-mail address and password
in order to create an account. The user can then update this information, and
choose to upload a profile picture.

Assumptions:
The user has a valid e-mail address

Acceptance:  
Users are assigned a UUID  
Passwords are hashed properly  
Users can create a unique username  
Users can update their account name  
Users can update their e-mail address  
Users can update their password  
Users can upload a profile picture  

--

**Friends List**:  
Size: Small

The user can add others to their friends list, users can than manage
their friends list.

Assumptions:
The user has an account, there are other users to add

Acceptance:  
Users can view all added friends  
Users can add friends either by username or email address  
Users can remove friends  

--

**Direct Chat**:  
Size: Small

The user can send messages directly to friends.

Assumptions:
The user has added another user to their friends list

Acceptance:  
Messages can only be viewed by assumed two users  
The latest 50 messages from the conversation will be displayed  
Older messages will be archived  
Timestamps are stored and displayed  

--

**User created Cliques**:  
(This section needs to be broken into smaller sections with greater detail)

Size: Large

User can create 1 personal clique and can be invited to join 2 additional
cliques. The user can recover their unique clique and create another
but they must terminate their clique to do so. User can opt to
leave their friends clique at any time.
Invites to cliques will only extended to users if a majority of the users in
a clique approve.

Assumptions:
The user has a friend to add to their clique

Acceptance:  
User can have 1 unique clique active at a time  
User can terminate their clique at any time and create another  
User can be invited to join up to 2 cliques created by other users  
Users can join a clique only if 75% of a cliques users approve  
The first invite sent by a clique is always approved  
Messages can be viewed by all users  
Messages will be timestamped  
Messages from the past 24 hours will be displayed  


