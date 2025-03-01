SUMMARY = "Display module for error logging"
DESCRIPTION = "A UDP based display module that receives messages from the server and displays it in the console."
LICENSE = "CLOSED"
SRC_URI = "file://display-module.cpp"


DEPENDS = "virtual/libc"

S = "${WORKDIR}"

# Adding LDFLAGS globally to ensure linker options are applied
CXXFLAGS += "-Wl,--hash-style=gnu"

do_compile() {
    ${CXX} ${S}/display-module.cpp -o ${S}/display-module ${CXXFLAGS}
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/display-module ${D}${bindir}
}


FILES:${PN} = "${bindir}/display-module"

