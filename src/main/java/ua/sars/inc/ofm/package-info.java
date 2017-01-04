@org.hibernate.annotations.GenericGenerator(
    name="ID_GENERATOR",
    strategy = "enhanced-sequence",
    parameters = {
        @org.hibernate.annotations.Parameter(
            name = "sequence_name",
            value = "ID_GEN_SEQ"
        ),
        //DDL option
        @org.hibernate.annotations.Parameter(
            name = "initial_value",
            value = "1000"
        )
    }
)

package ua.sars.inc.ofm;