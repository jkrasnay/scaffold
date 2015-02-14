
# Scaffold

Scaffold is a little Java tool for generating support classes for a
domain class.

Suppose you have a domain class like this...

    public class Widget {

        private int id;
        private String name;
        private String color;

        // Constructor and accessors omitted

    }

Further, suppose on your project you always create a DAO for each domain class.

    public interface WidgetDao {

        public void deleteById(int id);

        public Widget findById(int id);

        public void insert(Widget widget);

        public void update(Widget widget);

    }

Typing this boilerplate for each domain class can become tedious. With Scaffold,
you develop a set of Velocity templates representing files to be generated.
You can then invoke Scaffold generate new files matching your domain class.

In the example above, you might create the package `com.example.myapp.scaffold` and
within it create the following template with the name `DaoTemplate.txt`:

    public interface ${Entity}Dao {

        public void deleteById(int id);

        public ${Entity} findById(int id);

        public void insert(${Entity} ${entity});

        public void update(${Entity} ${entity});

    }

Next, you create a driver class for invoking Scaffold:

    public class MyScaffold extends Scaffold {

        public MyScaffold(Class<?> clazz) {
            super(clazz);
        }

        public static void main(String[] args) {
            new MyScaffold(Widget.class)
            .render("DaoTemplate.txt", "${Entity}Dao.java");
        }
    }

By default, templates are retrieved from the same package as the driver
class and output files are written to the same package as the domain
class (I'm a strong believer in the package-by-feature approach to
organizing source code, so that latter default makes sense to me.) Of
course you can override the default source and destination directories.

The following properties are available for substitution into the templates.

Property   | Description
-----------|-----------------------------------------
`package`  | Package of the entity class.
`Entity`   | Simple name (without package) of the entity class.
`entity`   | Simple name of the entity class with the first letter lower-cased.
`fields`   | List of `java.lang.reflect.Field` representing fields in the entity class.
`date`     | Current date, in `yyyy-mm-dd` format.
`scaffold` | Reference to the scaffold object itself.

The inclusion of the scaffold object allows you to implement utility methods
and use them in your templates. For example, suppose you were implementing a
template to generate Liquibase templates. In this case, you might need a method
to determine the database type corresponding to the type of your field:

    public class MyScaffold extends Scaffold {

        // ...

        public String dbType(Class<?> fieldType) {
            if (type == int.class || type == Integer.class) {
                return "int";
            } else if (type == boolean.class || type == Boolean.class) {
                return "boolean";
            } else {
                return "text";
            }
        }

    }

Your template could then contain something like this:

    #foreach ($field in $fields)
        <column name="$field.name.toLowerCase()" type="$scaffold.dbType($field.type)"/>
    #end


## License

Scaffold is licensed under the Apache License 2.0.


