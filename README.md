# Log2J

Log2J is a simple drop-in replacement for Log4J and SLF4J, that skips all configuration options and redirects everything hard wired to java.util.logging.
If not familiar with it, check out Oracle's JDK documentation or any other tutorial - there is plenty good documentation available. 

It does not aim to be 100% compatible with the original! Just enough, to provide logging for the most common java libraries. 
Worked fine for our erp, webshop and reporting products @ [BDM](https://bdm.wien) since 2015. After the log4j security nightmare in dec 2021, decided to publish it ... happy, if it helps anyone. If it does not suit your needs - stick with the original! The Log4J maintainers have
published security fixes.

## Features

* Really small, efficient and secure
* No support for log4j or slf4j configuration. Just plain hard-wired java.util.logging. 
  Nothing can go wrong, no attack surface, but you might loose some nifty logging features.
* Compatible with many java libraries including jackcess, jetty 10/11, smbj
* Also compatible with many deprecated libraries like jcifs or jxl
* JDK 1.8 required to compile/run

## Usage

To switch from log4j + slf4j to log2j you have to replace the libs in your build scripts. 
That depends on the used build tools (ant build.xml, maven pom.xml, lib-directories, ...).
Or the generated artifacts (WEB-INF/lib dir of .war files is a common place).

## License

_log2j_ is open source software. The source code is distributed under the terms of [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0).
