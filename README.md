# Scheduling System

Solution for simple scheduling system as specified for Rullion tech test. 

## Spec
As part of a scheduling system, we need to be able to determine the order to execute dependent
tasks after parsing their definitions.
Tasks are identified by a lowercase word. Dependencies between tasks are denoted using a =&gt; b
where a is a task that is dependent on b.
When a task has a dependency on one or more other tasks, those tasks must be executed first. If a
task has no dependencies it is executed in the order that it has been declared. If a cyclic dependency
is used, the system must detect it as an error and not enter an invalid state.
We estimate that there will never be more than 50 tasks at a time.

## Acceptance Criteria

```
   tasks : []
   dependencies: []
   result: []
   tasks: [a,b]
   dependencies: []
   result: [a,b]
   tasks: [a,b]
   dependencies: [a =&gt; b]
   result: [b,a]
   tasks: [a,b,c,d]
   dependencies: [a =&gt; b,c =&gt; d]
   result: [b,a,d,c]
   tasks: [a,b,c]
   dependencies: [a =&gt; b,b =&gt; c]
   result: [c,b,a]
   tasks: [a,b,c,d]
   dependencies: [a =&gt; b,b =&gt; c,c =&gt; a]
   result: Error - this is a cyclic dependency
   ```
   