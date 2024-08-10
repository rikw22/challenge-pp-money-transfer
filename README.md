
## Objective: Simplified Payment Method

This project it's a Simplified payment platform code challenge. It allows users to deposit and transfer money between each other. There are two types of users: `regular users` and `merchants`. Both have wallets with money and can transfer between them.

### Requirements

Business rules that are important for the Simplified Payment Method to work:

- For both user types, we need the `Full Name`, `Document`, `Email` and `Password`. Document and Emails must be unique in the system. Therefore, your system should only allow one registration with the same document or email address;

- Users can send money (make transfers) to merchants and between users;

- Merchants **only receive** transfers, they do not send money to anyone;

- Validate if the user has a balance before the transfer;

- Before finalizing the transfer, an external authorization service must be consulted. Use this mock [https://util.devi.tools/api/v2/authorize](https://util.devi.tools/api/v2/authorize) to simulate the service using the `GET` verb;

- The transfer operation must be a transaction (i.e., reversed in any case of inconsistency) and the money must return to the wallet of the user who sends it;

- Upon receiving payment, the user or merchant needs to receive a notification (email or SMS) sent by a third-party service. This service may be unavailable or unstable. Use this mock [https://util.devi.tools/api/v1/notify)](https://util.devi.tools/api/v1/notify)) to simulate sending the notification using the `POST` verb;

- This service should be RESTful.


### Transfer Endpoint

You can implement whatever you find convenient, but we will **only** focus on the transfer flow between two users. The implementation must follow the contract below.

```http request
POST /transfer
Content-Type: application/json

{
  "value": 100.0,
  "payer": 4,
  "payee": 15
}
```

If you find it interesting, make an **endpoint proposal** and present it to the interviewers

# Evaluation
Present your solution using the framework of your choice, justifying your choice.
Pay attention to fulfilling most of the requirements, as you may fulfill them partially and during the evaluation we will have a chat about what is missing.


## What will be evaluated and we value

Basic backend project creation skills:
- REST Knowledge 
- Git
- Analytical skills
- Clean and organized code

Intermediate knowledge of building maintainable projects:
- Adherence to implementation recommendations
- Application and knowledge of SOLID
- Identification and application of Design Patterns
- Basic understanding of cache operation and use
- Knowledge about container concepts (Docker, Podman etc)
- Documentation and description of functionalities and project handling
- Implementation and knowledge about unit and integration testing
- Identify and propose improvements
- Good notions of relational databases

Skills to create and maintain high-quality applications:
- Application of observability knowledge
- Use of CI to run tests and static analysis
- Knowledge about non-relational databases
- Application of architectures (CQRS, Event-sourcing, Microservices, Modular Monolith)
- Use and implementation of messaging
- Notions of scalability
- Good skills in applying business knowledge to software
- Implementation guided by quality tools (static analysis,  etc)

## What WILL NOT be evaluated
- User and merchant registration flow
- Frontend (we will only evaluate the (RESTful API)[https://www.devmedia.com.br/rest-tutorial/28912])
- Authentication

## What will be a Differential
- Use of Docker
- Consistent test coverage
- Use of Design Patterns
- Documentation
- Proposal for architectural improvement
- Be consistent and be able to argue your choices
- Present solutions you master
- Data Modeling
- Code Maintainability
- Error Handling
- Care with security items
- Architecture (structure your thinking before writing)
- Ensuring loose coupling between layers, services, and repositories for improved modularity

