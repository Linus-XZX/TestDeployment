# Why do I need this?

**You probably don't.**

For me, it sort of functions as a simplistic, skeleton reproduction of the actual project I am working on in `$DAYJOB`, which allows me to test some lower level changes before they go into company code. And by committing them here I get to, ummm, track changes and document stuff ig...

All data and real structure stripped, of course, but I don't need them for this purpose right now.

# What if I do?

Clone, **fill in `demo/src/main/resources/application.yml`**, `mvn install`, and run the resulting jar.

You will need to provide, at the very least:

- `server.port`
- `spring.datasource.{url,username,password}`

The `system.filePath` keys are project specific, and could probably be replaced with something manual if necessary.

# But... security issues/out of support components!

Not really much I can do here since I am specifically replicating the company environment, outside of minor version updates. I might try to put in major updates as well when I am confident enough that they don't break things.

# Mentions

[This entry](https://www.lxzx.dev/java-zips-and-encodings/) in my own blog. This is an actual issue it caught by the way.

# TODOs

- Keep throwing updates in...? Not that I can really break this thing.
- Try to set up multirelease between Java 8 and some later version (currently planning for 25).