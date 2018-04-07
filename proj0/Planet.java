public class Planet{
        public static double G = 6.67e-11;
        public double xxPos;
        public double yyPos;
        public double xxVel;
        public double yyVel;
        public double mass;
        public String imgFileName = "jupiter.gif";
        public Planet (double xP, double yP, double xV,
        double yV, double m, String img){
            xxPos = xP;
            yyPos = yP;
            xxVel = xV;
            yyVel = yV;
            mass = m;
            imgFileName = img;
        }
        public Planet(Planet p){
            xxPos = p.xxPos;
            yyPos = p.yyPos;
            xxVel = p.xxVel;
            yyVel = p.yyVel;
            mass = p.mass;
            imgFileName = p.imgFileName;
        }
        public double calcDistance(Planet p){
            double x = this.xxPos - p.xxPos;
            double y = this.yyPos - p.yyPos;
            double distance = Math.sqrt(x * x + y * y);
            return distance;
        }
        public double calcForceExertedBy(Planet p){
            double distance = this.calcDistance(p);
            double Force = -(G * this.mass * p.mass) / (distance * distance);
            return Force;
        }
        public double calcForceExertedByX(Planet p){
            double distance = this.calcDistance(p);
            double force = this.calcForceExertedBy(p);
            return force * ((this.xxPos - p.xxPos) / distance);
        }
        public double calcForceExertedByY(Planet p){
            return this.calcForceExertedBy(p) * ((this.yyPos - p.yyPos) / this.calcDistance(p));
        }
        public double calcNetForceExertedByX(Planet[] array){
            double netForceX = 0;
            //for loop's advanced methods.
            for (Planet x : array){
                if (!(this.equals(x)))
                    netForceX += calcForceExertedByX(x);
            }
            return netForceX;
        }
        public double calcNetForceExertedByY(Planet[] array){
            double netForceY = 0;
            for (int i = 0; i < array.length; i++){
                if (!(this.equals(array[i])))
                    netForceY += calcForceExertedByY(array[i]);
            }
            return netForceY;
        }
        public Planet update(double t, double xForce, double yForce){
            this.xxVel = this.xxVel + (xForce / this.mass) * t;
            this.xxPos = this.xxPos + this.xxVel * t;
            this.yyVel = this.yyVel + (yForce / this.mass) * t;
            this.yyPos = this.yyPos + this.yyVel * t;
            return this;
        }
        public void draw(){
            StdDraw.picture(this.xxPos, this.yyPos, "images/" + imgFileName);
        }
}
